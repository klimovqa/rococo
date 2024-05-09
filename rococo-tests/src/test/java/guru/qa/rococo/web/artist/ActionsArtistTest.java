package guru.qa.rococo.web.artist;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.ArtistPage;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.PaintingPage;
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

    private final MainPage mainPage = new MainPage();
    private final ArtistPage artistPage = new ArtistPage();
    private final PaintingPage paintingPage = new PaintingPage();

    @DisplayName("Добавление художника")
    @ApiLogin(
            username = "sacha",
            password = "12345")
    void artistShouldBeAddedTest() {
        final String ARTIST = "Рембрандт";
        mainPage.openMainPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.clickAddArtist();
        artistPage.enterArtistName(ARTIST);
        artistPage.uploadArtistPhoto("photo/artist/artist.jpeg");
        artistPage.enterArtistBiography("Рембрандт – голландский художник. Стал крупнейшим представителем Золотого века голландской живописи.");
        artistPage.clickAddArtistButton();
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Добавлен художник: " + ARTIST,
                "Добавлен художник: " + ARTIST);
        artistPage.enterArtistIntoSearch(ARTIST);
        artistPage.clickSearchButton();
        artistPage.checkNumberOfArtistsInSearchResults(1);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается именно " + ARTIST, ARTIST);
    }

    @DisplayName("Изменить художника")
    @ApiLogin(
            username = "maria",
            password = "12345")
    void artistShouldBeEditedTest() {
        final String ARTIST = "Серов, Валентин Александрович";
        final String BIO = "Серов, Валентин Александрович.";
        mainPage.openMainPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.clickArtistCard(ARTIST);
        artistPage.clickArtistEditButton();
        artistPage.enterArtistBiography(BIO);
        artistPage.clickArtistSaveButton();
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Обновлен художник: " + ARTIST,
                "Обновлен художник: " + ARTIST);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается измененное описание " + BIO, BIO);
    }

    @DisplayName("Добавление картины через форму художника")
    @ApiLogin(
            username = "sacha2",
            password = "12345")
    void addingPaintingsThroughArtistFormTest() {
        final String ARTIST = "Малевич, Казимир Северинович";
        final String PAINTING = "Черный квадрат";
        mainPage.openMainPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.clickArtistCard(ARTIST);
        artistPage.clickPaintingAddButton();


        paintingPage.enterNamePainting(PAINTING);
        paintingPage.selectMuseum("Эрмитаж");
        paintingPage.uploadPaintingContent("photo/painting/black.png");
        paintingPage.enterPaintingDescription("«Чёрный квадрат» — картина Казимира Малевича, созданная в 1915 году.");
        paintingPage.clickPaintingAddButton();
        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Добавлена картина: " + PAINTING,
                "Добавлена картина: " + PAINTING);

        mainPage.goToPaintingPage();
        paintingPage.enterPaintingIntoSearch(PAINTING);
        paintingPage.clickSearchButton();
        paintingPage.checkNumberOfPaintingInSearchResults(1);
        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается именно " + PAINTING, PAINTING);
    }
}
