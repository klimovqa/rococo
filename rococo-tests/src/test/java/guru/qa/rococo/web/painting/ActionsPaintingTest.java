package guru.qa.rococo.web.painting;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.MainPage;
import guru.qa.rococo.page.PaintingPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Epic("[WEB]")
@Feature("Картины")
@Story("Действия на странице Картины")
@DisplayName("[WEB] Действия на странице Картины")
@Tag("WEB")
public class ActionsPaintingTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final PaintingPage paintingPage = new PaintingPage();

    @DisplayName("Добавление картины")
    @ApiLogin(username = "nik",
            password = "12345")
    void paintingShouldBeAddedTest() {
        final String PAINTING = "Портрет старушки";
        mainPage.openMainPage();
        mainPage.goToPaintingPage();
        paintingPage.checkTitlePainting();
        paintingPage.clickAddPainting();
        paintingPage.enterNamePainting(PAINTING);
        paintingPage.selectArtist("Илья Ефимович Репин");
        paintingPage.selectMuseum("Эрмитаж");
        paintingPage.uploadPaintingContent("photo/painting/painting.jpeg");
        paintingPage.enterPaintingDescription("Портрет написан в период, когда Репин завершал свое образование в Академии художеств в Петербурге. Для участия в академическом конкурсе он писал полотно на евангельский сюжет «Воскрешение дочери Иаира» (1871, Русский музей). Работая над этим замыслом, художник штудировал образцы высокого классического искусства. Так появился «Портрет старушки» – копия с одноименной картины Рембрандта (1654, Эрмитаж). Тогда же художник исполнил большую графическую копию с «Портрета старика в красном» Рембрандта (начало 1870-х, Музей-усадьба «Пенаты).");
        paintingPage.clickPaintingAddButton();
//        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается тостер Добавлена картина: " + PAINTING,
//                "Добавлена картины: " + PAINTING);

        paintingPage.enterPaintingIntoSearch(PAINTING);
        paintingPage.clickSearchButton();
        paintingPage.checkNumberOfPaintingInSearchResults(1);
//        paintingPage.checkByTextShouldBeVisible("Проверяем что отображается именно " + PAINTING, PAINTING);
    }
}
