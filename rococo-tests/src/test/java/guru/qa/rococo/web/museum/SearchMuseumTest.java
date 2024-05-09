package guru.qa.rococo.web.museum;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.MuseumPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Feature("Музеи")
@Story("Поиск на странице Музеи")
@DisplayName("[WEB] Поиск на странице Музеи")
@Tag("WEB")
public class SearchMuseumTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final MuseumPage museumPage = new MuseumPage();

    @DisplayName("Поиск существующего музея")
    @Test
    void searchForMuseumThatExistsTest() {
        mainPage.openMainPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.enterMuseumIntoSearch("Эрмит");
        museumPage.clickSearchButton();
        museumPage.checkNumberOfMuseumsInSearchResults(1);
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается именно Эрмитаж",
                "Эрмитаж");
    }

    @DisplayName("Поиск не существующего музея")
    @Test
    void searchForMuseumThatNotExistsTest() {
        mainPage.openMainPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.enterMuseumIntoSearch("Ротердам");
        museumPage.clickSearchButton();
        museumPage.checkNumberOfMuseumsInSearchResults(0);
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается заглушка - Музеи не найдены",
                "Музеи не найдены");
    }
}
