package guru.qa.rococo.web.museum;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Epic("[WEB]")
@Feature("Музеи")
@Story("Действия на странице Музеи")
@DisplayName("[WEB] Действия на странице Музеи")
@Tag("WEB")
public class ActionsMuseumTest extends BaseTest {

    @DisplayName("Добавление музея")
    @ApiLogin(
            username = "misha",
            password = "12345")
    void museumShouldBeAddedTest() {
        final String MUSEUM = "Национальный музей икон Онуфрия";
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .checkTitleMuseum()
                .clickAddMuseum()
                .enterMuseumName(MUSEUM)
                .enterMuseumCountry("Албания")
                .enterMuseumCity("Берат")
                .uploadMuseumPhoto("photo/museum/museum.jpeg")
                .enterMuseumDescription("Национальный музей икон (иконографики) Онуфрия " +
                        "(алб. Muzeu Ikonografik Onufri) — национальный музей Албании, " +
                        "расположен в помещении церкви «Сон Девы Марии», в крепости города Берат.")
                .clickMuseumAddButton()
                .checkPopUpMuseum("Добавлен музей: " + MUSEUM)
                .enterMuseumIntoSearch(MUSEUM)
                .clickSearchButton()
                .checkNumberOfMuseumsInSearchResults(1)
                .checkMuseumDisplay(MUSEUM);
    }

    @DisplayName("Редактирование музея")
    @ApiLogin(
            username = "max",
            password = "12345")
    void editMuseumTest() {
        final String MUSEUM = "Британский музей";
        final String DESC = "Измененное описание";
        new MainPage()
                .openMainPage()
                .goToMuseumPage()
                .checkTitleMuseum()
                .enterMuseumIntoSearch(MUSEUM)
                .clickSearchButton()
                .clickMuseumCard(MUSEUM)
                .editMuseum()
                .enterMuseumDescription(DESC)
                .clickSaveMuseum()
                .checkPopUpMuseum("Обновлен музей: " + MUSEUM)
                .checkMuseumDisplay(DESC);
    }
}
