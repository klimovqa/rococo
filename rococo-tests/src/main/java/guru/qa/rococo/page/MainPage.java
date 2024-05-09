package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPage extends BasePage {

    private final static String DARK = "Toggle Dark Mode";
    private final static String LIGHT = "Toggle Light Mode";

    SelenideElement titleMainPage = $(byText("Ваши любимые картины и художники всегда рядом"));
    SelenideElement avatarUserEmpty = $(".avatar-initials");
    SelenideElement avatarUser = $(".avatar-image");
    SelenideElement popUpSession = $(byText("Сессия завершена"));
    SelenideElement popUpProfileUpdate = $(byText("Профиль обновлен"));

    @Step("Нажимаем кнопку Войти на главной странице")
    public LoginPage goToLogin() {
        signIn.click();
        return new LoginPage();
    }

    @Step("Открываем главную страницу Rococo")
    public MainPage openMainPage() {
        open(CFG.rococoFrontUrl());
        return this;
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

    @Step("Проверяем заголовок на главной странице Rococo")
    public MainPage checkTitleMainPage() {
        titleMainPage.shouldBe(visible);
        return this;
    }

    @Step("Проверяем что появилась аватарка")
    public MainPage checkAvatarVisible() {
        avatarUserEmpty.shouldBe(visible);
        return this;
    }

    @Step("Нажимаем на аватар и ждем пока появится МО с профилем")
    public UserPage clickAvatar() {
        avatarUserEmpty.shouldBe(visible, Duration.ofSeconds(10)).click();
        return new UserPage();
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

    @Step("Проверяем что отображается тостер Сессия завершена")
    public MainPage checkSessionIsOver() {
        popUpSession.shouldBe(visible);
        return new MainPage();
    }

    @Step("Проверяем что появился тостер Профиль обновлен")
    public MainPage checkUpdateProfile() {
        popUpProfileUpdate.shouldBe(visible);
        return this;
    }

    @Step("Проверяем что аватарка загружена")
    public MainPage checkAvatarImage() {
        avatarUser.shouldBe(visible);
        return this;
    }
}
