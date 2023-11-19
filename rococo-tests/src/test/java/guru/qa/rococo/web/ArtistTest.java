package guru.qa.rococo.web;

import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.ArtistPage;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Story("Художники")
@DisplayName("[WEB] Страница Художники")
@Tag("WEB")
public class ArtistTest extends BaseWebTest {


    private final MainPage mainPage = new MainPage();
    private final ArtistPage artistPage = new ArtistPage();

    @DisplayName("Отображение страницы Художники")
    @Test
    void checkViewedArtistPageTest() {
        mainPage.openPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.checkCountArtists(9);
    }

    @DisplayName("Поиск существующего Художника")
    @Test
    void searchForArtistThatExistsTest() {
        mainPage.openPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.inputSearch("винч");
        artistPage.searchClick();
        artistPage.checkCountArtists(1);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается именно - Леонардо да Винчи",
                "Леонардо да Винчи");
    }

    @DisplayName("Поиск не существующего Художника")
    @Test
    void searchForArtistThatNotExistsTest() {
        mainPage.openPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.inputSearch("Васнец");
        artistPage.searchClick();
        artistPage.checkCountArtists(0);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается заглушка - Художники не найдены",
                "Художники не найдены");
    }

    @DisplayName("Просмотр художника без картины")
    @Test
    void checkArtistCardWithoutTest() {
        final String ARTIST_NAME = "Клод Моне";
        final String TOSTER = "Пока что список картин этого художника пуст.";
        final String DESCRIPTION = "Оска́р Клод Моне́ (фр. Oscar-Claude Monet 14 ноября 1840, Париж, Франция — 5 декабря 1926, Живерни, Франция) — французский живописец, один из основателей импрессионизма.";
        mainPage.openPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.clickArtistCard(ARTIST_NAME);
        artistPage.checkNameOfArtistCard(ARTIST_NAME);
        artistPage.checkDescription(DESCRIPTION);
        artistPage.checkArtistNameOfArtistCard(ARTIST_NAME);
        artistPage.checkByTextShouldBeVisible("Проверить что отображается тостер " + TOSTER, TOSTER);
    }

    @DisplayName("Просмотр художника с картиной")
    @Test
    void checkArtistCardWithTest() {
        final String ARTIST_NAME = "Пабло Пикассо";
        final String PAINTING = "Старый еврей с мальчиком";
        final String DESCRIPTION = "Пабло Пикассо (полное имя — Пабло Диего Хосе Франсиско де Паула Хуан Непомусенo Мария де лос Ремедиос Сиприано де ла Сантисима Тринидад Мартир Патрисио Руис-и-Пикассо) — испанский и французский художник, скульптор, график, театральный художник";
        mainPage.openPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.clickArtistCard(ARTIST_NAME);
        artistPage.checkNameOfArtistCard(ARTIST_NAME);
        artistPage.checkDescription(DESCRIPTION);
        artistPage.checkArtistNameOfArtistCard(ARTIST_NAME);
        artistPage.checkByTextShouldBeVisible("Проверить что отображается картина " + PAINTING, PAINTING);
    }


    @DisplayName("Добавление художника")
    @ApiLogin(
            username = "sacha",
            password = "12345")
    void addArtistTest() {
        final String ARTIST = "Рембрандт";
        mainPage.openPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.clickAddArtist();
        artistPage.inputArtistName(ARTIST);
        artistPage.uploadArtistPhoto("photo/artist/artist.jpeg");
        artistPage.inputArtistBiography("Рембрандт – голландский художник. Стал крупнейшим представителем Золотого века голландской живописи.");
        artistPage.addedArtist();
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Добавлен художник: " + ARTIST,
                "Добавлен художник: " + ARTIST);

        artistPage.inputSearch(ARTIST);
        artistPage.searchClick();
        artistPage.checkCountArtists(1);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается именно " + ARTIST, ARTIST);
    }
}
