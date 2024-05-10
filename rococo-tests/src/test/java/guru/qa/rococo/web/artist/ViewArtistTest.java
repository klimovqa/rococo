package guru.qa.rococo.web.artist;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Feature("Художники")
@Story("Просмотр на странице Художники")
@DisplayName("[WEB] Просмотр на странице Художники")
@Tag("WEB")
public class ViewArtistTest extends BaseTest {

    @DisplayName("Отображение страницы Художники")
    @Test
    void checkViewedArtistPageTest() {
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .checkNumberOfArtistsGreaterThanOrEqual(8);
    }

    @DisplayName("Просмотр художника без картины")
    @Test
    void checkArtistCardWithoutTest() {
        final String ARTIST_NAME = "Клод Моне";
        final String TOSTER = "Пока что список картин этого художника пуст.";
        final String DESCRIPTION = "Оска́р Клод Моне́ (фр. Oscar-Claude Monet 14 ноября 1840, Париж, Франция — 5 декабря 1926, Живерни, Франция) — французский живописец, один из основателей импрессионизма.";
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .clickArtistCard(ARTIST_NAME)
                .checkViewOfArtistCard(ARTIST_NAME)
                .checkDescriptionOfArtistCard(DESCRIPTION)
                .checkArtistPhotoViewOfArtistCard(ARTIST_NAME)
                .checkPopUpArtist(TOSTER);
    }

    @DisplayName("Просмотр художника с картиной")
    @Test
    void checkArtistCardWithTest() {
        final String ARTIST_NAME = "Пабло Пикассо";
        final String PAINTING = "Старый еврей с мальчиком";
        final String DESCRIPTION = "Пабло Пикассо (полное имя — Пабло Диего Хосе Франсиско де Паула Хуан Непомусенo Мария де лос Ремедиос Сиприано де ла Сантисима Тринидад Мартир Патрисио Руис-и-Пикассо) — испанский и французский художник, скульптор, график, театральный художник";
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .clickArtistCard(ARTIST_NAME)
                .checkViewOfArtistCard(ARTIST_NAME)
                .checkDescriptionOfArtistCard(DESCRIPTION)
                .checkArtistPhotoViewOfArtistCard(ARTIST_NAME)
                .checkEmptyMessageDisplay(PAINTING);
    }
}
