package guru.qa.rococo.jupiter.extension;

import guru.qa.rococo.db.entity.auth.AuthUserEntity;
import guru.qa.rococo.db.entity.auth.Authority;
import guru.qa.rococo.db.entity.auth.AuthorityEntity;
import guru.qa.rococo.db.repository.UserRepository;
import guru.qa.rococo.db.repository.imp.UserRepositoryImp;
import guru.qa.rococo.jupiter.annotation.CreateUser;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateUserExtension implements BeforeEachCallback {
    @Override
    @Step("Создаем пользователя для теста")
    public void beforeEach(ExtensionContext extension) throws Exception {
        if (extension.getRequiredTestMethod().isAnnotationPresent(CreateUser.class)) {
            CreateUser ann = extension.getRequiredTestMethod().getAnnotation(CreateUser.class);
            UserRepository userRepository = new UserRepositoryImp();

            AuthUserEntity authUser = new AuthUserEntity();
            authUser.setUsername(ann.username());
            authUser.setPassword(ann.password());
            authUser.setEnabled(true);
            authUser.setAccountNonExpired(true);
            authUser.setAccountNonLocked(true);
            authUser.setCredentialsNonExpired(true);
            authUser.setAuthorities(new ArrayList<>(Arrays.stream(Authority.values())
                    .map(a -> {
                        AuthorityEntity ae = new AuthorityEntity();
                        ae.setAuthority(a);
                        ae.setUser(authUser);
                        return ae;
                    }).toList()));

            userRepository.createUser(authUser);
        }
    }
}
