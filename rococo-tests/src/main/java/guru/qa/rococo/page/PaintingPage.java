package guru.qa.rococo.page;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class PaintingPage extends BasePage{

    public void openPage() {
        step("Открываем страницу Картины", () ->
                open(CFG.rococoFrontUrl() + "/painting"));
    }
}
