package guru.qa.rococo.page;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MuseumPage extends BasePage {
    SelenideElement titleMuseum = $(".text-3xl.m-4");
    SelenideElement enterTitleMuseum = $("input[name='title']");
    SelenideElement enterCity = $("input[name='city']");
    SelenideElement descriptionMuseum = $("textarea[name='description']");
    SelenideElement selectMuseum = $("select.select");
    SelenideElement viewMuseumName = $("header.card-header");
    SelenideElement viewAddress = $("article div.text-center");
    SelenideElement addMuseumButton = $(byText("Добавить музей"));

    @Step("Проверяем что отображается title - Музеи")
    public MuseumPage checkTitleMuseum() {
        titleMuseum.shouldBe(text("Музеи"));
        return this;
    }

    @Step("Проверяем количество отображаемых Музеев, должно быть - {count}")
    public MuseumPage checkNumberOfMuseumsInSearchResults(int count) {
        resultOfSearch.shouldHave(size(count));
        return this;
    }

    @Step("Вводим название музея - {name}")
    public MuseumPage enterMuseumIntoSearch(String name) {
        inputSearch.shouldBe(visible).val(name);
        return this;
    }

    @Step("Вводим название музея - {name}")
    public MuseumPage enterMuseumName(String name) {
        enterTitleMuseum.shouldBe(visible).val(name);
        return this;
    }

    @Step("Вводим название города - {city}")
    public MuseumPage enterMuseumCity(String city) {
        enterCity.shouldBe(visible).val(city);
        return this;
    }

    @Step("Вводим описание музея")
    public MuseumPage enterMuseumDescription(String desc) {
        descriptionMuseum.shouldBe(visible).val(desc);
        return this;
    }

    @Step("Выбираем страну - {country}")
    public MuseumPage enterMuseumCountry(String country) {
        selectMuseum.selectOptionContainingText(country);
        return this;
    }

    @Step("Загружаем фото музея")
    public MuseumPage uploadMuseumPhoto(String pathPhoto) {
        file.uploadFromClasspath(pathPhoto);
        return this;
    }

    @Step("Нажимаем кнопку Добавить")
    public MuseumPage clickMuseumAddButton() {
        addButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Сохранить")
    public MuseumPage clickSaveMuseum() {
        saveButton.click();
        return this;
    }

    @Step("Нажимаем на поиск")
    public MuseumPage clickSearchButton() {
        searchButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на музей - {name}")
    public MuseumPage clickMuseumCard(String name) {
        $("img[alt='" + name + "']").shouldBe(visible).click();
        return this;
    }

    @Step("Проверить что отображается картинка у музея - {name}")
    public MuseumPage checkMuseumNameOfMuseumCard(String name) {
        $("img[alt='" + name + "']").shouldBe(visible);
        return this;
    }

    @Step("Проверяем что в карточке отображается - {museumName}")
    public MuseumPage checkNameOfMuseumCard(String museumName) {
        viewMuseumName.shouldBe(text(museumName));
        return this;
    }

    @Step("Проверяем что в карточке отображается адрес - {address}")
    public MuseumPage checkAddress(String address) {
        viewAddress.shouldBe(text(address));
        return this;
    }

    @Step("Проверяем что в карточке отображается описание")
    public MuseumPage checkDescription(String description) {
        $(byText(description)).shouldBe(visible);
        return this;
    }

    @Step("Нажимаем Добавить музей")
    public MuseumPage clickAddMuseum() {
        addMuseumButton.click();
        return this;
    }

    @Step("Нажимаем Редактировать")
    public MuseumPage editMuseum() {
        editButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверяем что отображается тостер Добавлен музей")
    public MuseumPage checkPopUpMuseum(String textPopUp) {
        $(byText(textPopUp)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что отображается именно {museum}")
    public MuseumPage checkMuseumDisplay(String museum) {
        $(byText(museum)).shouldBe(visible);
        return this;
    }
}
