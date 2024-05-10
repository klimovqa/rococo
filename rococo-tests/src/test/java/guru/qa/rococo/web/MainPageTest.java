package guru.qa.rococo.web;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Feature("Главная страница")
@Story("Главная страница")
@DisplayName("[WEB] Главная страница")
@Tag("WEB")
public class MainPageTest extends BaseTest {

    @DisplayName("Проверка главной страницы")
    @Test
    void checkMainPageTest() {
        new MainPage()
                .openMainPage()
                .checkTitleMainPage()
                .checkByTextShouldBeVisible("Проверяем отображение надписи Картины",
                        "Картины")
                .checkByTextShouldBeVisible("Проверяем отображение надписи Музеи",
                        "Музеи")
                .checkByTextShouldBeVisible("Проверяем отображение надписи Художники",
                        "Художники")
                .checkLinkOfMainPage("/artist")
                .checkLinkOfMainPage("/museum")
                .checkLinkOfMainPage("/painting")
                .checkLinkOfMenu("/artist")
                .checkLinkOfMenu("/museum")
                .checkLinkOfMenu("/painting");
    }

    @DisplayName("Проверка переключения темы")
    @Test
    void checkChangeThemeTest() {
        new MainPage()
                .openMainPage()
                .getDefaultTheme()
                .changeThemeClick()
                .checkDarkTheme()
                .changeThemeClick()
                .checkLightTheme();
    }

    @DisplayName("Проверка линка логотипа на главной страницы")
    @Test
    void checkCheckLinkMainPageTest() {
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .goToMainPage()
                .checkTitleMainPage()
                .goToArtistPage()
                .goToMainPage()
                .checkTitleMainPage()
                .goToPaintingPage()
                .goToMainPage()
                .checkTitleMainPage();
    }
}
