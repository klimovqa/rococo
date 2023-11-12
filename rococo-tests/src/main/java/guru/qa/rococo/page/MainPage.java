package guru.qa.rococo.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPage extends BasePage {
    public void openPage() {
        step("Открываем главную страницу Rococo", () ->
                open(CFG.rococoFrontUrl()));
    }

    public void checkLinkOfMainPage(String link) {
        step("Проверяем ссылку на главной странице " + link, () ->
                $("#page-content a[href='" + link + "']")).shouldBe(exist);
    }

    public void checkLinkOfMenu(String link) {
        step("Проверяем ссылку в меню " + link, () ->
                $("nav.list-nav a[href='"+link+"']")).shouldBe(exist);
    }

}
