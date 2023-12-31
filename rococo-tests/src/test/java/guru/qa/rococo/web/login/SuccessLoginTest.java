package guru.qa.rococo.web.login;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.CreateUser;
import guru.qa.rococo.page.LoginPage;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


@Epic("[WEB]")
@Feature("Авторизация")
@Story("Успешный вход/регистрация нового пользователя")
@DisplayName("[WEB] Успешный вход/регистрация нового пользователя")
@Tag("WEB")
public class SuccessLoginTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();

    private final static String USERNAME = "igor666";
    private final static String USERNAME2 = "igor222";
    private final static String PASSWORD = "12345";

    @DisplayName("Вход под зарегистрированным пользователем")
    @CreateUser(username = USERNAME,
            password = PASSWORD)
    @Test
    void signInRegisteredUserTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.enterUsername(USERNAME);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLogInButton();
        loginPage.checkAvatarExist();
    }

    @DisplayName("Регистрация пользователя (положительный кейс)")
    @Test
    void userRegistrationPositiveCaseTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.clickOnRegisterLink();
        loginPage.enterUsername("USER_REG_POSITIVE");
        loginPage.enterPassword(PASSWORD);
        loginPage.enterPasswordSubmit(PASSWORD);
        loginPage.clickLogInButton();
        loginPage.checkSuccessRegistered();
    }

    private static Stream<Arguments> provideSuccessRegistration() {
        return Stream.of(
                Arguments.of("USER_REG_PASSWORD_LENGTH", "123"),
                Arguments.of("USER_REG_PASSWORD_LENGTH2", "123456789012"));
    }

    @ParameterizedTest(name = "Успешная регистрация пользователя с граничными значения пароля {0} {1}")
    @MethodSource("provideSuccessRegistration")
    void userRegistrationPasswordLengthSuccessTest(String login, String password) {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.clickOnRegisterLink();
        loginPage.enterUsername(login);
        loginPage.enterPassword(password);
        loginPage.enterPasswordSubmit(password);
        loginPage.clickLogInButton();
        loginPage.checkSuccessRegistered();
    }

    @DisplayName("Вход через кнопку войти на форме регистрации нового пользователя")
    @CreateUser(username = USERNAME2,
            password = PASSWORD)
    @Test
    void loginViaLoginButtonOnNewUserRegistrationFormTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.clickOnRegisterLink();
        loginPage.signIn();
        loginPage.enterUsername(USERNAME2);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLogInButton();
        mainPage.checkTitleMainPage();
    }
}
