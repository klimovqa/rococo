package guru.qa.rococo.web;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.ArtistPage;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.MuseumPage;
import guru.qa.rococo.page.PaintingPage;
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

    private final MainPage mainPage = new MainPage();
    private final MuseumPage museumPage = new MuseumPage();
    private final ArtistPage artistPage = new ArtistPage();
    private final PaintingPage paintingPage = new PaintingPage();

    @DisplayName("Проверка главной страницы")
    @Test
    void checkMainPageTest(){
        mainPage.openMainPage();
        mainPage.checkTitleMainPage();
        mainPage.checkByTextShouldBeVisible("Проверяем отображение надписи Картины",
                "Картины");
        mainPage.checkByTextShouldBeVisible("Проверяем отображение надписи Музеи",
                "Музеи");
        mainPage.checkByTextShouldBeVisible("Проверяем отображение надписи Художники",
                "Художники");
        mainPage.checkLinkOfMainPage("/artist");
        mainPage.checkLinkOfMainPage("/museum");
        mainPage.checkLinkOfMainPage("/painting");
        mainPage.checkLinkOfMenu("/artist");
        mainPage.checkLinkOfMenu("/museum");
        mainPage.checkLinkOfMenu("/painting");
    }

    @DisplayName("Проверка переключения темы")
    @Test
    void checkChangeThemeTest(){
        mainPage.openMainPage();
        String theme = mainPage.getCurrentTheme();
        mainPage.checkChangeTheme(theme);
        mainPage.changeThemeClick();
        String theme2 = mainPage.getCurrentTheme();
        mainPage.checkChangeTheme(theme2);
        mainPage.changeThemeClick();
        String theme3 = mainPage.getCurrentTheme();
        mainPage.checkChangeTheme(theme3);
    }

    @DisplayName("Проверка линка логотипа на главной страницы")
    @Test
    void checkCheckLinkMainPageTest(){
        museumPage.openPage();
        mainPage.goToMainPage();
        mainPage.checkTitleMainPage();

        artistPage.openPage();
        mainPage.goToMainPage();
        mainPage.checkTitleMainPage();

        paintingPage.openPage();
        mainPage.goToMainPage();
        mainPage.checkTitleMainPage();
    }
}
