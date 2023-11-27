package guru.qa.rococo.web.login;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.CreateUser;
import guru.qa.rococo.page.LoginPage;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.UserPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Epic("[WEB]")
@Feature("Авторизация")
@Story("Действия в личном кабинете")
@DisplayName("[WEB] Действия в личном кабинете")
@Tag("WEB")
public class ProfileTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();
    private final UserPage userPage = new UserPage();
    private final static String USERNAME_LOGOUT = "igor888";
    private final static String USERNAME3 = "igor333";
    private final static String PASSWORD = "12345";

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
