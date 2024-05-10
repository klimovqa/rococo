package guru.qa.rococo.web.museum;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.MainPage;
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

    @DisplayName("Поиск существующего музея")
    @Test
    void searchForMuseumThatExistsTest() {
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .checkTitleMuseum()
                .enterMuseumIntoSearch("Эрмит")
                .clickSearchButton()
                .checkNumberOfMuseumsInSearchResults(1)
                .checkMuseumDisplay("Эрмитаж");
    }

    @DisplayName("Поиск не существующего музея")
    @Test
    void searchForMuseumThatNotExistsTest() {
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .checkTitleMuseum()
                .enterMuseumIntoSearch("Ротердам")
                .clickSearchButton()
                .checkNumberOfMuseumsInSearchResults(0)
                .checkMuseumDisplay("Музеи не найдены");
    }
}
