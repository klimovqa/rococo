package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ArtistPage extends BasePage {
    SelenideElement titleArtist = $(".text-3xl.m-4");
    SelenideElement artistName = $("input[name='name']");
    SelenideElement biography = $("textarea[name='biography']");
    SelenideElement biographyDescription = $("section > p");
    SelenideElement viewArtistName = $("header.card-header");
    SelenideElement imageArtist = $("section img");
    SelenideElement addPaintingButton = $("section.justify-items-center button.variant-filled-primary");


    @Step("Проверяем что отображается title - Художники")
    public ArtistPage checkTitleArtist() {
        titleArtist.shouldBe(text("Художники"));
        return this;
    }

    @Step("Проверяем количество отображаемых Художников, должно быть - {count}")
    public ArtistPage checkNumberOfArtistsInSearchResults(int count) {
        resultOfSearch.shouldHave(size(count));
        return this;
    }

    @Step("Проверяем количество отображаемых Художников, должно быть больше или равно - {count}")
    public ArtistPage checkNumberOfArtistsGreaterThanOrEqual(int count) {
        resultOfSearch.shouldHave(sizeGreaterThanOrEqual(count));
        return this;
    }

    @Step("Вводим название художника - {name}")
    public ArtistPage enterArtistIntoSearch(String name) {
        inputSearch.val(name);
        return this;
    }

    @Step("Нажимаем на поиск")
    public ArtistPage clickSearchButton() {
        searchButton.click();
        return this;
    }

    @Step("Нажать на художника - {name}")
    public ArtistPage clickArtistCard(String name) {
        $(byText(name)).click();
        return this;
    }

    @Step("Проверить что отображается картинка у художника - {name}")
    public ArtistPage checkArtistPhotoViewOfArtistCard(String name) {
        imageArtist.shouldBe(visible);
        return this;
    }

    @Step("Проверяем что в карточке отображается - {artistName}")
    public ArtistPage checkViewOfArtistCard(String artistName) {
        viewArtistName.shouldBe(text(artistName));
        return this;
    }

    @Step("Проверяем что в карточке отображается описание")
    public ArtistPage checkDescriptionOfArtistCard(String description) {
        $(byText(description)).shouldBe(visible);
        return this;
    }

    @Step("Нажимаем Добавить художника")
    public ArtistPage clickAddArtist() {
        $(byText("Добавить художника")).click();
        return this;
    }

    @Step("Нажимаем Добавить картину")
    public PaintingPage clickPaintingAddButton() {
        addPaintingButton.click();
        return new PaintingPage();
    }

    @Step("Вводим имя художника - {name}")
    public ArtistPage enterArtistName(String name) {
        artistName.val(name);
        return this;
    }

    @Step("Вводим описание художника")
    public ArtistPage enterArtistBiography(String bio) {
        biography.val(bio);
        return this;
    }

    @Step("Загружаем фото картины")
    public ArtistPage uploadArtistPhoto(String pathPhoto) {
        file.uploadFromClasspath(pathPhoto);
        return this;
    }

    @Step("Нажимаем кнопку Добавить")
    public ArtistPage clickAddArtistButton() {
        addButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Сохранить")
    public ArtistPage clickArtistSaveButton() {
        saveButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Редактировать")
    public ArtistPage clickArtistEditButton() {
        editButton.click();
        return this;
    }

    @Step("Проверяем что отображается тостер Добавлен художник")
    public ArtistPage checkPopUpArtist(String textPopUp) {
        $(byText(textPopUp)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что отображается именно {artist}")
    public ArtistPage checkArtistDisplay(String artist) {
        $(byText(artist)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что отображается именно {msg}")
    public ArtistPage checkEmptyMessageDisplay(String msg) {
        $(byText(msg)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что отображается измененное описание {bio}")
    public ArtistPage checkDisplayedByChangeBio(String bio) {
        biographyDescription.shouldBe(text(bio));
        return this;
    }
}
