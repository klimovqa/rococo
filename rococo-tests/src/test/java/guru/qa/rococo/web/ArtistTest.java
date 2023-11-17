package guru.qa.rococo.web;

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
        artistPage.checkCountArtists(6);
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
}
