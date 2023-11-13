package guru.qa.rococo.page;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ArtistPage extends BasePage{

    public void openPage() {
        step("Открываем страницу Художники", () ->
                open(CFG.rococoFrontUrl() + "/artist"));
    }
}
