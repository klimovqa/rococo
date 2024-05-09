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
@Story("Просмотр на странице Картины")
@DisplayName("[WEB] Просмотр на странице Картины")
@Tag("WEB")
public class ViewPaintingTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final PaintingPage paintingPage = new PaintingPage();

    @DisplayName("Отображение страницы Картины")
    @Test
    void checkViewedPaintingPageTest() {
        mainPage.openMainPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.checkNumberOfPaintingInSearchResults(4);
    }

    @DisplayName("Просмотр Картины")
    @Test
    void checkPaintingCardTest() {
        final String PAINTING_NAME = "Большой рейд в Кронштадте";
        final String ARTIST = "Иван Константинович Айвазовский";
        final String DESCRIPTION = "Большой рейд в Кронштадте. Айвазовский И. К. 1817, Феодосия – 1900, там же. ... Экспонировалась на выставке 1836 в ИАХ под названием \"Вид части Кронштадта с идущим на парусах стопушечным кораблем в бурную погоду\"";
        mainPage.openMainPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.clickPaintingCard(PAINTING_NAME);
        paintingPage.checkNameOfPaintingCard(PAINTING_NAME);
        paintingPage.checkArtist(ARTIST);
        paintingPage.checkDescription(DESCRIPTION);
        paintingPage.checkPaintingNameOfPaintingCard(PAINTING_NAME);
    }
}
