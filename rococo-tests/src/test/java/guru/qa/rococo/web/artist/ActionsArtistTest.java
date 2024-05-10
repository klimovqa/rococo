package guru.qa.rococo.web.artist;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Epic("[WEB]")
@Feature("Художники")
@Story("Действия на странице Художники")
@DisplayName("[WEB] Действия на странице Художники")
@Tag("WEB")
public class ActionsArtistTest extends BaseTest {

    @DisplayName("Добавление художника")
    @ApiLogin(
            username = "sacha",
            password = "12345")
    void artistShouldBeAddedTest() {
        final String ARTIST = "Рембрандт";
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .clickAddArtist()
                .enterArtistName(ARTIST)
                .uploadArtistPhoto("photo/artist/artist.jpeg")
                .enterArtistBiography("Рембрандт – голландский художник. " +
                        "Стал крупнейшим представителем Золотого века голландской живописи.")
                .clickAddArtistButton()
                .checkPopUpArtist("Добавлен художник: " + ARTIST)
                .enterArtistIntoSearch(ARTIST)
                .clickSearchButton()
                .checkNumberOfArtistsInSearchResults(1)
                .checkArtistDisplay(ARTIST);
    }

    @DisplayName("Изменить художника")
    @ApiLogin(
            username = "maria",
            password = "12345")
    void artistShouldBeEditedTest() {
        final String ARTIST = "Серов, Валентин Александрович";
        final String BIO = "Серов, Валентин Александрович.";
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .clickArtistCard(ARTIST)
                .clickArtistEditButton()
                .enterArtistBiography(BIO)
                .clickArtistSaveButton()
                .checkPopUpArtist("Обновлен художник: " + ARTIST)
                .checkDisplayedByChangeBio(BIO);
    }

    @DisplayName("Добавление картины через форму художника")
    @ApiLogin(
            username = "sacha2",
            password = "12345")
    void addingPaintingsThroughArtistFormTest() {
        final String ARTIST = "Малевич, Казимир Северинович";
        final String PAINTING = "Черный квадрат";
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .clickArtistCard(ARTIST)
                .clickPaintingAddButton()
                .enterNamePainting(PAINTING)
                .selectMuseum("Эрмитаж")
                .uploadPaintingContent("photo/painting/black.png")
                .enterPaintingDescription("«Чёрный квадрат» — картина Казимира Малевича," +
                        " созданная в 1915 году.")
                .clickPaintingAddButton()
                .checkPopUpPainting("Добавлена картина: " + PAINTING);

        new MainPage()
                .goToPaintingPage()
                .enterPaintingIntoSearch(PAINTING)
                .clickSearchButton()
                .checkNumberOfPaintingInSearchResults(1)
                .checkPaintingDisplay(PAINTING);
    }
}
