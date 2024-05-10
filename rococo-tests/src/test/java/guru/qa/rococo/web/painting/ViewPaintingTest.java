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
@Story("Просмотр на странице Картины")
@DisplayName("[WEB] Просмотр на странице Картины")
@Tag("WEB")
public class ViewPaintingTest extends BaseTest {

    @DisplayName("Отображение страницы Картины")
    @Test
    void checkViewedPaintingPageTest() {
        new MainPage()
                .openMainPage()
                .goToPaintingPage()
                .checkTitlePainting()
                .checkNumberOfPaintingInSearchResults(4);
    }

    @DisplayName("Просмотр Картины")
    @Test
    void checkPaintingCardTest() {
        final String PAINTING_NAME = "Большой рейд в Кронштадте";
        final String ARTIST = "Иван Константинович Айвазовский";
        final String DESCRIPTION = "Большой рейд в Кронштадте. Айвазовский И. К. 1817, Феодосия – 1900, там же. ... Экспонировалась на выставке 1836 в ИАХ под названием \"Вид части Кронштадта с идущим на парусах стопушечным кораблем в бурную погоду\"";
        new MainPage()
                .openMainPage()
                .goToPaintingPage()
                .checkTitlePainting()
                .clickPaintingCard(PAINTING_NAME)
                .checkNameOfPaintingCard(PAINTING_NAME)
                .checkArtist(ARTIST)
                .checkDescription(DESCRIPTION)
                .checkPaintingNameOfPaintingCard(PAINTING_NAME);
    }
}
