package guru.qa.rococo.web;

import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;



@Epic("[WEB]")
@DisplayName("[WEB] Главная страница")
@Tag("WEB")
public class MainPageTest extends BaseWebTest {

    private final MainPage mainPage = new MainPage();
    @DisplayName("Проверка главной страницы")
    @Test
    void checkMainPageTest(){
        mainPage.openPage();
        mainPage.checkByTextShouldBeVisible("Проверяем заголовок на главной странице Rococo",
                "Ваши любимые картины и художники всегда рядом");
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
}
