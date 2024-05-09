package guru.qa.rococo.web.artist;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.ArtistPage;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Feature("Художники")
@Story("Поиск на странице Художники")
@DisplayName("[WEB] Поиск на странице Художники")
@Tag("WEB")
public class SearchArtistTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final ArtistPage artistPage = new ArtistPage();

    @DisplayName("Поиск существующего Художника")
    @Test
    void searchForArtistThatExistsTest() {
        mainPage.openMainPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.enterArtistIntoSearch("винч");
        artistPage.clickSearchButton();
        artistPage.checkNumberOfArtistsInSearchResults(1);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается именно - Леонардо да Винчи",
                "Леонардо да Винчи");
    }

    @DisplayName("Поиск не существующего Художника")
    @Test
    void searchForArtistThatNotExistsTest() {
        mainPage.openMainPage();
        mainPage.goToArtistPage();
        artistPage.checkTitleArtist();
        artistPage.enterArtistIntoSearch("Васнец");
        artistPage.clickSearchButton();
        artistPage.checkNumberOfArtistsInSearchResults(0);
        artistPage.checkByTextShouldBeVisible("Проверяем что отображается заглушка - Художники не найдены",
                "Художники не найдены");
    }
}
