package guru.qa.rococo.web.login;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.CreateUser;
import guru.qa.rococo.page.MainPage;
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
    private final static String USERNAME_LOGOUT = "igor888";
    private final static String USERNAME3 = "igor333";
    private final static String PASSWORD = "dqfwefqwefqwe23";

    @DisplayName("Разлогин пользователя")
    @CreateUser(username = USERNAME_LOGOUT,
            password = PASSWORD)
    @Test
    void userLogoutTest() {
        new MainPage().openMainPage()
                .goToLogin()
                .enterUsername(USERNAME_LOGOUT)
                .enterPassword(PASSWORD)
                .clickEnterButton()
                .checkAvatarVisible()
                .clickAvatar()
                .clickLogout()
                .checkSessionIsOver();
    }

    @DisplayName("Заполнение профиля у зарегистрированного пользователя")
    @CreateUser(username = USERNAME3,
            password = PASSWORD)
    @Test
    void fillUserProfileTest() {
        new MainPage().openMainPage()
                .goToLogin()
                .enterUsername(USERNAME3)
                .enterPassword(PASSWORD)
                .clickEnterButton()
                .clickAvatar()
                .uploadAvatar()
                .enterFirstname("Иван")
                .enterSurname("Петров")
                .clickProfileUpdateButton()
                .checkUpdateProfile()
                .checkAvatarImage();
    }
}
