package guru.qa.rococo.web;

import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.PaintingPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Story("Картины")
@DisplayName("[WEB] Страница Картины")
@Tag("WEB")
public class PaintingTest extends BaseWebTest {


    private final MainPage mainPage = new MainPage();
    private final PaintingPage paintingPage = new PaintingPage();

    @DisplayName("Отображение страницы Картины")
    @Test
    void checkViewedPaintingPageTest() {
        mainPage.openPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.checkCountPaintings(4);
    }

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


    @DisplayName("Просмотр Картины")
    @Test
    void checkPaintingCardTest() {
        final String PAINTING_NAME = "Большой рейд в Кронштадте";
        final String ARTIST = "Иван Константинович Айвазовский";
        final String DESCRIPTION = "Большой рейд в Кронштадте. Айвазовский И. К. 1817, Феодосия – 1900, там же. ... Экспонировалась на выставке 1836 в ИАХ под названием \"Вид части Кронштадта с идущим на парусах стопушечным кораблем в бурную погоду\"";
        mainPage.openPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.clickPaintingCard(PAINTING_NAME);
        paintingPage.checkNameOfPaintingCard(PAINTING_NAME);
        paintingPage.checkArtist(ARTIST);
        paintingPage.checkDescription(DESCRIPTION);
        paintingPage.checkPaintingNameOfPaintingCard(PAINTING_NAME);
    }

    @DisplayName("Добавление картины")
    @ApiLogin(
            username = "nik",
            password = "12345")
    void addPaintingTest() {
        final String PAINTING = "Портрет старушки";
        mainPage.openPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.clickAddPainting();
        paintingPage.inputPaintingName(PAINTING);
        paintingPage.selectArtist("Илья Ефимович Репин");
        paintingPage.selectMuseum("Эрмитаж");
        paintingPage.uploadPaintingContent("photo/painting/painting.jpeg");
        paintingPage.inputPaintingDescription("Портрет написан в период, когда Репин завершал свое образование в Академии художеств в Петербурге. Для участия в академическом конкурсе он писал полотно на евангельский сюжет «Воскрешение дочери Иаира» (1871, Русский музей). Работая над этим замыслом, художник штудировал образцы высокого классического искусства. Так появился «Портрет старушки» – копия с одноименной картины Рембрандта (1654, Эрмитаж). Тогда же художник исполнил большую графическую копию с «Портрета старика в красном» Рембрандта (начало 1870-х, Музей-усадьба «Пенаты).");
        paintingPage.addedPainting();
        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Добавлена картина: " + PAINTING,
                "Добавлена картины: " + PAINTING);

        paintingPage.inputSearch(PAINTING);
        paintingPage.searchClick();
        paintingPage.checkCountPaintings(1);
        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается именно " + PAINTING, PAINTING);
    }
}
