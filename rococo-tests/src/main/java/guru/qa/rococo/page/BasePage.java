package guru.qa.rococo.page;

import guru.qa.rococo.config.Config;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class BasePage {
    protected static final Config CFG = Config.getInstance();

    public void checkByTextShouldBeVisible(String step, String text) {
        step(step, () ->
                $(byText(text)).shouldBe(visible));
    }
}

