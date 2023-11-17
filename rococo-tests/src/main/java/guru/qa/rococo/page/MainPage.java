package guru.qa.rococo.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPage extends BasePage {

    private final static String DARK = "Toggle Dark Mode";
    private final static String LIGHT = "Toggle Light Mode";

    public void goToLogin() {
        step("Нажимаем кнопку Войти на главной странице", () ->
                $(byText("Войти")).click());
    }

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
                $("nav.list-nav a[href='" + link + "']")).shouldBe(exist);
    }

    public String getCurrentTheme() {
        String currentTheme = step("Получаем текущею тему", () ->
                $("div[aria-checked]").attr("aria-checked"));
        return currentTheme.equals("false") ? LIGHT : DARK;
    }

    public void changeThemeClick() {
        step("Изменяем тему", () ->
                $("div[role='switch']")).click();
    }

    public void checkChangeTheme(String themeMode) {
        step("Проверяем что текущая тема " + (themeMode.equals(LIGHT) ? DARK : LIGHT), () ->
                $("div[role='switch'][title='" + themeMode + "']")).shouldBe(exist);
    }

    public void goToMainPage() {
        step("Переходим на главную страницу", () ->
                $("a[href='/'")).click();
    }

    public void checkTitleMainPage() {
        step("Проверяем заголовок на главной странице Rococo", () ->
                $(byText("Ваши любимые картины и художники всегда рядом")).shouldBe(visible));
    }


    public void goToMuseumPage() {
        step("Переходим на страницу Музеи", () ->
                $("header a[href='/museum']").click());
    }

    public void goToPaintingPage() {
        step("Переходим на страницу Картинки", () ->
                $("header a[href='/painting']").click());
    }

    public void goToArtistPage() {
        step("Переходим на страницу Художники", () ->
                $("header a[href='/artist']").click());
    }
}
