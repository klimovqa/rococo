package guru.qa.rococo.web.painting;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Feature("Картины")
@Story("Поиск на странице Картины")
@DisplayName("[WEB] Поиск на странице Картины")
@Tag("WEB")
public class SearchPaintingTest extends BaseTest {

    @DisplayName("Поиск существующей картины")
    @Test
    void searchForPaintingThatExistsTest() {
        new MainPage()
                .openMainPage()
                .goToPaintingPage()
                .checkTitlePainting()
                .enterPaintingIntoSearch("евре")
                .clickSearchButton()
                .checkNumberOfPaintingInSearchResults(1)
                .checkPaintingDisplay("Старый еврей с мальчиком");
    }

    @DisplayName("Поиск не существующей картины")
    @Test
    void searchForPaintingThatNotExistsTest() {
        new MainPage()
                .openMainPage()
                .goToPaintingPage()
                .checkTitlePainting()
                .enterPaintingIntoSearch("Васнецлдл")
                .clickSearchButton()
                .checkNumberOfPaintingInSearchResults(0)
                .checkPaintingDisplay("Картины не найдены");
    }
}
