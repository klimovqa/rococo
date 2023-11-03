package guru.qa.rococo.jupiter.extension;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.rococo.api.AuthClient;
import guru.qa.rococo.api.context.ThreadLocalCookieStore;
import guru.qa.rococo.config.Config;
import guru.qa.rococo.db.entity.auth.AuthUserEntity;
import guru.qa.rococo.db.entity.auth.Authority;
import guru.qa.rococo.db.entity.auth.AuthorityEntity;
import guru.qa.rococo.db.entity.userdata.UserDataEntity;
import guru.qa.rococo.db.repository.UserRepository;
import guru.qa.rococo.db.repository.UserRepositoryImp;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.util.OauthUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ApiLoginExtension implements BeforeEachCallback, AfterEachCallback {

    public static final Config CFG = Config.getInstance();

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(ApiLoginExtension.class);

    private final AuthClient authClient = new AuthClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ApiLogin apiLogin = AnnotationSupport
                .findAnnotation(
                        context.getRequiredTestMethod(), ApiLogin.class
                ).orElse(
                        AnnotationSupport
                                .findAnnotation(context.getRequiredTestClass(), ApiLogin.class)
                                .orElse(null)
                );

        if (apiLogin != null) {
            setCodeVerifier(context, OauthUtils.generateCodeVerifier());
            setCodeChallenge(context, OauthUtils.generateCodeChallenge(getCodeVerifier(context)));
            UserRepository repository = new UserRepositoryImp();
            UserDataEntity entity = repository.findByUsername(apiLogin.username());
            if (entity == null) {
                repository.createUser(convertToEntity(apiLogin));
            }
            authClient.doLogin(context, apiLogin.username(), apiLogin.password());
            Selenide.open(CFG.rococoFrontUrl());
            Selenide.localStorage().setItem("id_token", getToken(context));
            WebDriverRunner.getWebDriver().manage().addCookie(getJsessionIdCookie());
            Selenide.refresh();
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        ThreadLocalCookieStore.INSTANCE.removeAll();
    }

    public static void setCodeVerifier(ExtensionContext extensionContext, String codeVerifier) {
        extensionContext.getStore(NAMESPACE).put("cv", codeVerifier);
    }

    public static String getCodeVerifier(ExtensionContext extensionContext) {
        return extensionContext.getStore(NAMESPACE).get("cv", String.class);
    }

    public static void setCodeChallenge(ExtensionContext context, String codeChallenge) {
        context.getStore(NAMESPACE).put("cc", codeChallenge);
    }

    public static String getCodeChallenge(ExtensionContext context) {
        return context.getStore(NAMESPACE).get("cc", String.class);
    }

    public static void setCode(ExtensionContext context, String code) {
        context.getStore(NAMESPACE).put("code", code);
    }

    public static String getCode(ExtensionContext context) {
        return context.getStore(NAMESPACE).get("code", String.class);
    }

    public static void setToken(ExtensionContext context, String token) {
        context.getStore(NAMESPACE).put("token", token);
    }

    public static String getToken(ExtensionContext context) {
        return context.getStore(NAMESPACE).get("token", String.class);
    }

    public static String getBearerToken(ExtensionContext context) {
        return "Bearer " + getToken(context);
    }

    public static String getCsrf() {
        return ThreadLocalCookieStore.INSTANCE.cookieValue("XSRF-TOKEN");
    }

    public static String getJsessionid() {
        return ThreadLocalCookieStore.INSTANCE.cookieValue("JSESSIONID");
    }

    public Cookie getJsessionIdCookie() {
        return new Cookie(
                "JSESSIONID",
                getJsessionid()
        );
    }

    public static String getJsessionIdCookieAsString() {
        return "JSESSIONID=" + getJsessionid();
    }

    private AuthUserEntity convertToEntity(ApiLogin apiLogin) {
        AuthUserEntity user = new AuthUserEntity();
        user.setUsername(apiLogin.username());
        user.setPassword(apiLogin.password());


        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities(new ArrayList<>(Arrays.stream(Authority.values())
                .map(a -> {
                    AuthorityEntity ae = new AuthorityEntity();
                    ae.setAuthority(a);
                    ae.setUser(user);
                    return ae;
                }).collect(Collectors.toList())));
        return user;
    }
}
