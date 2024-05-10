package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PaintingPage extends BasePage {
    SelenideElement painting = $("input[name='title']");
    SelenideElement museumSelected = $("select.select[name=museumId]");
    SelenideElement description = $("textarea[name='description']");

    public PaintingPage openPage() {
        step("Открываем страницу Картины", () -> {
            open(CFG.rococoFrontUrl() + "/painting");
            $(byText("Картины")).shouldBe(visible);
        });
        return this;
    }


    public PaintingPage checkTitlePainting() {
        step("Проверяем что отображается title - Картины", () ->
                $(".text-3xl.m-4").shouldBe(text("Картины")));
        return this;
    }

    @Step("Проверяем количество отображаемых Картин, должно быть - {count}")
    public PaintingPage checkNumberOfPaintingInSearchResults(int count) {
        resultOfSearch.shouldHave(size(count));
        return this;
    }

    @Step("Вводим название картины - {name}")
    public PaintingPage enterPaintingIntoSearch(String name) {
        inputSearch.val(name);
        return this;
    }

    @Step("Нажимаем на поиск")
    public PaintingPage clickSearchButton() {
        searchButton.click();
        return this;
    }

    public PaintingPage clickPaintingCard(String name) {
        step("Нажать на художника - " + name, () ->
                $("img[alt='" + name + "']").click());
        return this;
    }

    public PaintingPage checkPaintingNameOfPaintingCard(String name) {
        step("Проверить что отображается картинка у картины - " + name, () ->
                $("img[alt='" + name + "']").shouldBe(visible));
        return this;
    }

    public PaintingPage checkNameOfPaintingCard(String museumName) {
        step("Проверяем что в карточке отображается - " + museumName, () ->
                $("header.card-header").shouldBe(text(museumName)));
        return this;
    }

    public PaintingPage checkArtist(String artist) {
        step("Проверяем что в карточке отображается художник - " + artist, () ->
                $("article div.text-center").shouldBe(text(artist)));
        return this;
    }

    public PaintingPage checkDescription(String description) {
        step("Проверяем что в карточке отображается описание", () ->
                $(byText(description)).shouldBe(visible));
        return this;
    }

    public PaintingPage clickAddPainting() {
        step("Нажимаем Добавить картину", () ->
                $(byText("Добавить картину")).click());
        return this;
    }

    @Step("Вводим название картины - {name}")
    public PaintingPage enterNamePainting(String name) {
        painting.val(name);
        return this;
    }

    @Step("Выбираем музей - {museum}")
    public PaintingPage selectMuseum(String museum) {
        museumSelected.selectOptionContainingText(museum);
        return this;
    }

    @Step("Вводим описание картины")
    public PaintingPage enterPaintingDescription(String desc) {
        description.val(desc);
        return this;
    }

    public PaintingPage selectArtist(String artist) {
        step("Выбираем художника - " + artist, () ->
                $("select.select[name=authorId]").selectOptionContainingText(artist));
        return this;
    }

    @Step("Загружаем фото картины")
    public PaintingPage uploadPaintingContent(String pathPhoto) {
        file.uploadFromClasspath(pathPhoto);
        return this;
    }

    @Step("Нажимаем кнопку Добавить")
    public PaintingPage clickPaintingAddButton() {
        addButton.click();
        return this;
    }

    @Step("Проверяем что отображается тостер Добавлена картина")
    public PaintingPage checkPopUpAddPainting(String textPopUp) {
        $(byText(textPopUp)).shouldBe(visible);
        return this;
    }


    @Step("Проверяем что отображается именно {painting}")
    public PaintingPage checkPaintingDisplay(String painting) {
        $(byText(painting)).shouldBe(visible);
        return this;
    }

}
