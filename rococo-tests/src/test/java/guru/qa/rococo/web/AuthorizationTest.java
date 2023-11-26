package guru.qa.rococo.web;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.CreateUser;
import guru.qa.rococo.page.LoginPage;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.UserPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


@Epic("[WEB]")
@Story("Авторизация")
@DisplayName("[WEB] Авторизация")
@Tag("WEB")
public class AuthorizationTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();
    private final UserPage userPage = new UserPage();

    private final static String USERNAME = "igor666";
    private final static String USERNAME2 = "igor222";
    private final static String USERNAME_NOT_UNIQ = "igor777";
    private final static String USERNAME_LOGOUT = "igor888";
    private final static String USERNAME3 = "igor333";
    private final static String PASSWORD = "12345";

    @DisplayName("Вход под зарегистрированным пользователем")
    @CreateUser(username = USERNAME,
            password = PASSWORD)
    @Test
    void signInRegisteredUserTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.inputUsername(USERNAME);
        loginPage.inputPassword(PASSWORD);
        loginPage.clickSubmit();
        loginPage.checkAvatarExist();
    }

    @DisplayName("Вход под НЕ зарегистрированным пользователем")
    @Test
    void signInNotRegisteredUserTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.inputUsername("NOT_REGISTERED_USER");
        loginPage.inputPassword(PASSWORD);
        loginPage.clickSubmit();
        loginPage.checkInvalidUserCredentials();
    }

    @DisplayName("Регистрация пользователя (положительный кейс)")
    @Test
    void userRegistrationPositiveCaseTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.inputUsername("USER_REG_POSITIVE");
        loginPage.inputPassword(PASSWORD);
        loginPage.inputPasswordSubmit(PASSWORD);
        loginPage.clickSubmit();
        loginPage.checkSuccessRegistered();
    }

    @DisplayName("Регистрация пользователя (пароли не совпадают)")
    @Test
    void userRegistrationPasswordDontMatchTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.inputUsername("USER_REG_PASSWORD_DONT_MATCH");
        loginPage.inputPassword("12345");
        loginPage.inputPasswordSubmit("123456");
        loginPage.clickSubmit();
        loginPage.checkErrorPasswordShouldBeEqual();
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
        loginPage.registerClick();
        loginPage.inputUsername(login);
        loginPage.inputPassword(password);
        loginPage.inputPasswordSubmit(password);
        loginPage.clickSubmit();
        loginPage.checkSuccessRegistered();
    }

    private static Stream<Arguments> provideErrorRegistration() {
        return Stream.of(
                Arguments.of("USER_REG_PASSWORD_LENGTH", "12", "Allowed password length should be from 3 to 12 characters"),
                Arguments.of("USER_REG_PASSWORD_LENGTH2", "1234567890123", "Allowed password length should be from 3 to 12 characters"));
    }

    @ParameterizedTest(name = "Пользователь должен получить сообщение об ошибке : {2} при вводе некорректных данных")
    @MethodSource("provideErrorRegistration")
    void userRegistrationPasswordLengthTest(String login, String password, String error) {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.inputUsername(login);
        loginPage.inputPassword(password);
        loginPage.inputPasswordSubmit(password);
        loginPage.clickSubmit();
        loginPage.checkError(error);
    }

    @DisplayName("Регистрация пользователя (пароль не введен повторно)")
    @Test
    void userRegistrationPasswordWastEnteredAgainTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.inputUsername("USER_REG_PASSWORD_WAS_NOT_ENTERED_AGAIN");
        loginPage.inputPassword("12345");
        loginPage.clickSubmit();
        loginPage.checkNotSuccessRegistered();
    }

    @DisplayName("Регистрация пользователя (пользователь с таким логином существует)")
    @CreateUser(username = USERNAME_NOT_UNIQ,
            password = PASSWORD)
    @Test
    void userRegistrationUserWithThisUsernameExistsTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.inputUsername(USERNAME_NOT_UNIQ);
        loginPage.inputPassword(PASSWORD);
        loginPage.inputPasswordSubmit(PASSWORD);
        loginPage.clickSubmit();
        loginPage.checkErrorNotUniqueUsername(USERNAME_NOT_UNIQ);
    }

    @DisplayName("Разлогин пользователя")
    @CreateUser(username = USERNAME_LOGOUT,
            password = PASSWORD)
    @Test
    void userLogoutTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.inputUsername(USERNAME_LOGOUT);
        loginPage.inputPassword(PASSWORD);
        loginPage.clickSubmit();
        loginPage.clickAvatar(USERNAME_LOGOUT);
        loginPage.clickLogout();
        loginPage.checkSessionIsOver();
    }

    @DisplayName("Вход через кнопку войти на форме регистрации нового пользователя")
    @CreateUser(username = USERNAME2,
            password = PASSWORD)
    @Test
    void loginViaLoginButtonOnNewUserRegistrationFormTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.signIn();
        loginPage.inputUsername(USERNAME2);
        loginPage.inputPassword(PASSWORD);
        loginPage.clickSubmit();
        mainPage.checkTitleMainPage();
    }


    @DisplayName("Заполнение профиля у зарегистрированного пользователя")
    @CreateUser(username = USERNAME3,
            password = PASSWORD)
    @Test
    void fillUserProfileTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.inputUsername(USERNAME3);
        loginPage.inputPassword(PASSWORD);
        loginPage.clickSubmit();
        loginPage.clickAvatar(USERNAME3);
        userPage.uploadAvatar();
        userPage.fillFirstname("Иван");
        userPage.fillSurname("Петров");
        userPage.updateProfileClick();
        userPage.checkUpdateProfile();
        userPage.checkAvatarImage();
    }
}
