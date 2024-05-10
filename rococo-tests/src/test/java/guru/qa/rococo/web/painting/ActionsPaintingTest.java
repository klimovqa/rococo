package guru.qa.rococo.web.painting;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.page.MainPage;
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

    @DisplayName("Добавление картины")
    @ApiLogin(username = "nik",
            password = "12345")
    void paintingShouldBeAddedTest() {
        final String PAINTING = "Портрет старушки";
        new MainPage()
                .openMainPage()
                .goToPaintingPage()
                .checkTitlePainting()
                .clickAddPainting()
                .enterNamePainting(PAINTING)
                .selectArtist("Илья Ефимович Репин")
                .selectMuseum("Эрмитаж")
                .uploadPaintingContent("photo/painting/painting.jpeg")
                .enterPaintingDescription("Портрет написан в период, " +
                        "когда Репин завершал свое образование в Академии художеств в Петербурге. " +
                        "Для участия в академическом конкурсе он писал полотно на евангельский сюжет " +
                        "«Воскрешение дочери Иаира» (1871, Русский музей). Работая над этим замыслом, " +
                        "художник штудировал образцы высокого классического искусства. Так появился " +
                        "«Портрет старушки» – копия с одноименной картины Рембрандта (1654, Эрмитаж). " +
                        "Тогда же художник исполнил большую графическую копию с «Портрета старика в " +
                        "красном» Рембрандта (начало 1870-х, Музей-усадьба «Пенаты).")
                .clickPaintingAddButton()
                .checkPopUpPainting("Добавлена картины: " + PAINTING)
                .enterPaintingIntoSearch(PAINTING)
                .clickSearchButton()
                .checkNumberOfPaintingInSearchResults(1)
                .checkPaintingDisplay(PAINTING);
    }
}
