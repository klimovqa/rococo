package guru.qa.rococo.web;

import guru.qa.rococo.page.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Story("Музеи")
@DisplayName("[WEB] Страница Музеи")
@Tag("WEB")
public class MuseumTest extends BaseWebTest {


    private final MainPage mainPage = new MainPage();
    private final MuseumPage museumPage = new MuseumPage();

    @DisplayName("Отображение страницы Музеи")
    @Test
    void checkViewedMuseumPageTest() {
        mainPage.openPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.checkCountMuseums(4);
    }

    @DisplayName("Поиск существующего музея")
    @Test
    void searchForMuseumThatExistsTest() {
        mainPage.openPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.inputSearch("Эрмит");
        museumPage.searchClick();
        museumPage.checkCountMuseums(1);
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается именно Эрмитаж",
                "Эрмитаж");
    }

    @DisplayName("Поиск не существующего музея")
    @Test
    void searchForMuseumThatNotExistsTest() {
        mainPage.openPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.inputSearch("Ротердам");
        museumPage.searchClick();
        museumPage.checkCountMuseums(0);
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается заглушка - Музеи не найдены",
                "Музеи не найдены");
    }
}
