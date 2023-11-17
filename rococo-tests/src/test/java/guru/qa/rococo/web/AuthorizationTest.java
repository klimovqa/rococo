package guru.qa.rococo.web;

import guru.qa.rococo.jupiter.annotation.CreateUser;
import guru.qa.rococo.page.LoginPage;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Epic("[WEB]")
@Story("Авторизация")
@DisplayName("[WEB] Авторизация")
@Tag("WEB")
public class AuthorizationTest extends BaseWebTest {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();

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
    @DisplayName("Регистрация пользователя (проверка граничных значений)")
    @Test
    void userRegistrationPasswordLengthTest() {
        mainPage.openPage();
        mainPage.goToLogin();
        loginPage.registerClick();
        loginPage.inputUsername("USER_REG_PASSWORD_LENGTH");
        loginPage.inputPassword("12");
        loginPage.inputPasswordSubmit("12");
        loginPage.clickSubmit();
        loginPage.checkError("Allowed password length should be from 3 to 12 characters");

        loginPage.inputPassword("1234567890123");
        loginPage.inputPasswordSubmit("1234567890123");
        loginPage.clickSubmit();
        loginPage.checkError("Allowed password length should be from 3 to 12 characters");

        loginPage.inputPassword("123");
        loginPage.inputPasswordSubmit("123");
        loginPage.clickSubmit();
        loginPage.checkSuccessRegistered();

        loginPage.signInSystem();
        loginPage.registerClick();
        loginPage.inputUsername("USER_REG_PASSWORD_LENGTH2");
        loginPage.inputPassword("123456789012");
        loginPage.inputPasswordSubmit("123456789012");
        loginPage.clickSubmit();
        loginPage.checkSuccessRegistered();
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
        loginPage.uploadAvatar();
        loginPage.fillFirstname("Иван");
        loginPage.fillSurname("Петров");
        loginPage.updateProfileClick();
        loginPage.checkUpdateProfile();
        loginPage.checkAvatarImage();
    }
}
