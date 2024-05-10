package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PaintingPage extends BasePage {
    SelenideElement painting = $("input[name='title']");
    SelenideElement museumSelected = $("select.select[name=museumId]");
    SelenideElement description = $("textarea[name='description']");
    SelenideElement titlePainting = $(".text-3xl.m-4");
    SelenideElement viewPaintingName = $("header.card-header");
    SelenideElement viewArtist = $("article div.text-center");
    SelenideElement selectArtist = $("select.select[name=authorId]");

    @Step("Проверяем что отображается title - Картины")
    public PaintingPage checkTitlePainting() {
        titlePainting.shouldBe(text("Картины"));
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

    @Step("Нажать на художника - {name}")
    public PaintingPage clickPaintingCard(String name) {
        $("img[alt='" + name + "']").click();
        return this;
    }

    @Step("Проверить что отображается картинка у картины - {name}")
    public void checkPaintingNameOfPaintingCard(String name) {
        $("img[alt='" + name + "']").shouldBe(visible);
    }

    @Step("Проверяем что в карточке отображается - {museumName}")
    public PaintingPage checkNameOfPaintingCard(String museumName) {
        viewPaintingName.shouldBe(text(museumName));
        return this;
    }

    @Step("Проверяем что в карточке отображается художник - {artist}")
    public PaintingPage checkArtist(String artist) {
        viewArtist.shouldBe(text(artist));
        return this;
    }

    @Step("Проверяем что в карточке отображается описание")
    public PaintingPage checkDescription(String description) {
        $(byText(description)).shouldBe(visible);
        return this;
    }

    @Step("Нажимаем Добавить картину")
    public PaintingPage clickAddPainting() {
        $(byText("Добавить картину")).shouldBe(visible).click();
        return this;
    }

    @Step("Вводим название картины - {name}")
    public PaintingPage enterNamePainting(String name) {
        painting.shouldBe(visible).val(name);
        return this;
    }

    @Step("Выбираем музей - {museum}")
    public PaintingPage selectMuseum(String museum) {
        museumSelected.selectOptionContainingText(museum);
        return this;
    }

    @Step("Вводим описание картины")
    public PaintingPage enterPaintingDescription(String desc) {
        description.shouldBe(visible).val(desc);
        return this;
    }

    @Step("Выбираем художника - {artist}")
    public PaintingPage selectArtist(String artist) {
        selectArtist.selectOptionContainingText(artist);
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
    public PaintingPage checkPopUpPainting(String textPopUp) {
        $(byText(textPopUp)).shouldBe(visible);
        return this;
    }


    @Step("Проверяем что отображается именно {painting}")
    public void checkPaintingDisplay(String painting) {
        $(byText(painting)).shouldBe(visible);
    }
}
