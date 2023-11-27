package guru.qa.rococo.web.museum;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.MuseumPage;
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

    private final MainPage mainPage = new MainPage();
    private final MuseumPage museumPage = new MuseumPage();

    @DisplayName("Добавление музея")
    @ApiLogin(
            username = "misha",
            password = "12345")
    void addMuseumTest() {
        final String MUSEUM = "Национальный музей икон Онуфрия";
        mainPage.openPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.clickAddMuseum();
        museumPage.inputMuseumName(MUSEUM);
        museumPage.inputMuseumCountry("Албания");
        museumPage.inputMuseumCity("Берат");
        museumPage.uploadMuseumPhoto("photo/museum/museum.jpeg");
        museumPage.inputMuseumDescription("Национальный музей икон (иконографики) Онуфрия (алб. Muzeu Ikonografik Onufri) — национальный музей Албании, расположен в помещении церкви «Сон Девы Марии», в крепости города Берат.");
        museumPage.addedMuseum();
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Добавлен музей: " + MUSEUM,
                "Добавлен музей: " + MUSEUM);

        museumPage.inputSearch(MUSEUM);
        museumPage.searchClick();
        museumPage.checkCountMuseums(1);
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается именно " + MUSEUM, MUSEUM);
    }

    @DisplayName("Редактирование музея")
    @ApiLogin(
            username = "max",
            password = "12345")
    void editMuseumTest() {
        final String MUSEUM = "Британский музей";
        final String DESC = "Измененное описание";
        mainPage.openPage();
        mainPage.goToMuseumPage();
        museumPage.checkTitleMuseum();
        museumPage.inputSearch(MUSEUM);
        museumPage.searchClick();
        museumPage.clickMuseumCard(MUSEUM);
        museumPage.editMuseum();
        museumPage.inputMuseumDescription(DESC);
        museumPage.saveMuseum();
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Обновлен музей: " + MUSEUM,
                "Обновлен музей: " + MUSEUM);
        museumPage.checkByTextShouldBeVisible("Проверяем что отображается измененное описание " + DESC, DESC);
    }
}
