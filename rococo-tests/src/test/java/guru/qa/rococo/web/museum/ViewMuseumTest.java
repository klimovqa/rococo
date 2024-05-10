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
@Story("Просмотр на странице Музеи")
@DisplayName("[WEB] Просмотр на странице Музеи")
@Tag("WEB")
public class ViewMuseumTest extends BaseTest {


    @DisplayName("Отображение страницы Музеи")
    @Test
    void checkViewedMuseumPageTest() {
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .checkTitleMuseum()
                .checkNumberOfMuseumsInSearchResults(4);
    }

    @DisplayName("Просмотр Музея")
    @Test
    void checkMuseumCardTest() {
        final String MUSEUM_NAME = "Эрмитаж";
        final String ADDRESS = "Россия, Санкт-Петербург";
        final String DESCRIPTION = "Эрмита́ж (от фр. ermitage — место уединения, келья, приют отшельника, затворничество), Госуда́рственный Эрмита́ж[2] (сокр. ГЭ; до 1917 года — Императорский Эрмитаж) — российский государственный художественный и культурно-исторический музей в Санкт-Петербурге, одно из крупнейших в мире учреждений подобного рода[";
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .checkTitleMuseum()
                .clickMuseumCard(MUSEUM_NAME)
                .checkNameOfMuseumCard(MUSEUM_NAME)
                .checkAddress(ADDRESS)
                .checkDescription(DESCRIPTION)
                .checkMuseumNameOfMuseumCard(MUSEUM_NAME);
    }
}
