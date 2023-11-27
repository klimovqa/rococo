package guru.qa.rococo.web.painting;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.PaintingPage;
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


    private final MainPage mainPage = new MainPage();
    private final PaintingPage paintingPage = new PaintingPage();

    @DisplayName("Поиск существующей картины")
    @Test
    void searchForPaintingThatExistsTest() {
        mainPage.openPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.inputSearch("евре");
        paintingPage.searchClick();
        paintingPage.checkCountPaintings(1);
        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается именно - Старый еврей с мальчиком",
                "Старый еврей с мальчиком");
    }

    @DisplayName("Поиск не существующей картины")
    @Test
    void searchForPaintingThatNotExistsTest() {
        mainPage.openPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.inputSearch("Васнецлдл");
        paintingPage.searchClick();
        paintingPage.checkCountPaintings(0);
        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается заглушка - Картины не найдены",
                "Картины не найдены");
    }
}
