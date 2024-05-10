package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage {

    SelenideElement titleMainPage = $(byText("Ваши любимые картины и художники всегда рядом"));
    SelenideElement avatarUserEmpty = $(".avatar-initials");
    SelenideElement avatarUser = $(".avatar-image");
    SelenideElement popUpSession = $(byText("Сессия завершена"));
    SelenideElement popUpProfileUpdate = $(byText("Профиль обновлен"));
    SelenideElement museumLink = $("header a[href='/museum']");
    SelenideElement artistLink = $("header a[href='/artist']");
    SelenideElement paintingLink = $("header a[href='/painting']");
    SelenideElement lightMode = $("div[role='switch'][aria-checked='true']");
    SelenideElement darkMode = $("div[role='switch'][aria-checked='false']");
    SelenideElement toggle = $("div[role='switch']");

    @Step("{step}")
    public MainPage checkByTextShouldBeVisible(String step, String text) {
        $(byText(text)).shouldBe(visible);
        return this;
    }

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

    @Step("Проверяем ссылку на главной странице {link}")
    public MainPage checkLinkOfMainPage(String link) {
        $("#page-content a[href='" + link + "']").shouldBe(visible);
        return this;
    }

    @Step("Проверяем ссылку в меню {link}")
    public MainPage checkLinkOfMenu(String link) {
        $("nav.list-nav a[href='" + link + "']").shouldBe(visible);
        return this;
    }

    @Step("Устанавливаем светлую тему, если не установлена")
    public MainPage getDefaultTheme() {
        toggle.should(visible);
        if (!lightMode.exists()) {
            toggle.click();
            lightMode.shouldBe(exist);
        }
        lightMode.shouldBe(exist);
        return this;
    }

    @Step("Изменяем тему")
    public MainPage changeThemeClick() {
        toggle.click();
        return this;
    }

    @Step("Проверяем что текущая тема Dark")
    public MainPage checkDarkTheme() {
        darkMode.shouldBe(exist);
        return this;
    }
    @Step("Проверяем что текущая тема Light")
    public MainPage checkLightTheme() {
        lightMode.shouldBe(exist);
        return this;
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

    @Step("Переходим на страницу Музеи")
    public MuseumPage goToMuseumPage() {
        museumLink.click();
        return new MuseumPage();
    }

    @Step("Переходим на страницу Художники")
    public ArtistPage goToArtistPage() {
        artistLink.click();
        return new ArtistPage();
    }

    @Step("Переходим на страницу Картины")
    public PaintingPage goToPaintingPage() {
        paintingLink.click();
        return new PaintingPage();
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
