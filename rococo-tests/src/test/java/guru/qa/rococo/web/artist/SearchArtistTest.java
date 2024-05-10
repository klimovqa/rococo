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
@Story("Поиск на странице Художники")
@DisplayName("[WEB] Поиск на странице Художники")
@Tag("WEB")
public class SearchArtistTest extends BaseTest {

    @DisplayName("Поиск существующего Художника")
    @Test
    void searchForArtistThatExistsTest() {
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .enterArtistIntoSearch("винч")
                .clickSearchButton()
                .checkNumberOfArtistsInSearchResults(1)
                .checkArtistDisplay("Леонардо да Винчи");
    }

    @DisplayName("Поиск не существующего Художника")
    @Test
    void searchForArtistThatNotExistsTest() {
        new MainPage()
                .openMainPage()
                .goToArtistPage()
                .checkTitleArtist()
                .enterArtistIntoSearch("Васнец")
                .clickSearchButton()
                .checkNumberOfArtistsInSearchResults(0)
                .checkArtistDisplay("Художники не найдены");
    }
}
