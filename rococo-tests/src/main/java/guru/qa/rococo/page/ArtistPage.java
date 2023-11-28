package guru.qa.rococo.page;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ArtistPage extends BasePage {

    public void openPage() {
        step("Открываем страницу Художники", () -> {
            open(CFG.rococoFrontUrl() + "/artist");
            $(byText("Художники")).shouldBe(visible);
        });
    }

    public void checkTitleArtist() {
        step("Проверяем что отображается title - Художники", () ->
                $(".text-3xl.m-4").shouldBe(text("Художники")));
    }

    public void checkNumberOfArtistsInSearchResults(int count) {
        step("Проверяем количество отображаемых Художников, должно быть - " + count, () ->
                $$(".w-100 li").shouldHave(size(count)));
    }

    public void enterArtistIntoSearch(String name) {
        step("Вводим название художника - " + name, () ->
                $("input.input").val(name));
    }

    public void clickSearchButton() {
        step("Нажимаем на поиск", () ->
                $("button img[alt='Иконка поиска']").click());
    }


    public void clickArtistCard(String name) {
        step("Нажать на художника - " + name, () ->
                $(byText(name)).click());
    }

    public void checkArtistPhotoViewOfArtistCard(String name) {
        step("Проверить что отображается картинка у художника - " + name, () ->
                $("section img").shouldBe(visible));
    }

    public void checkViewOfArtistCard(String artistName) {
        step("Проверяем что в карточке отображается - " + artistName, () ->
                $("header.card-header").shouldBe(text(artistName)));
    }

    public void checkDescriptionOfArtistCard(String description) {
        step("Проверяем что в карточке отображается описание", () ->
                $(byText(description)).shouldBe(visible));
    }
    
    public void clickAddArtist() {
        step("Нажимаем Добавить художника", () ->
                $(byText("Добавить художника")).click());
    }

    public void clickPaintingAddButton() {
        step("Нажимаем Добавить картину", () ->
                $(byText("Добавить картину")).click());
    }


    public void enterArtistName(String name) {
        step("Вводим имя художника - " + name, () ->
                $("input[name='name']").val(name));
    }

    public void enterArtistBiography(String bio) {
        step("Вводим описание художника", () ->
                $("textarea[name='biography']").val(bio));
    }

    public void uploadArtistPhoto(String pathPhoto) {
        step("Загружаем фото картины", () ->
                $("input[type='file'][name]")
                        .uploadFromClasspath(pathPhoto));
    }

    public void clickAddArtistButton() {
        step("Нажимаем кнопку Добавить", () ->
                $(byText("Добавить"))
                        .click());
    }

    public void clickArtistSaveButton() {
        step("Нажимаем кнопку Сохранить", () ->
                $(byText("Сохранить"))
                        .click());
    }

    public void clickArtistEditButton() {
        step("Нажимаем кнопку Редактировать", () ->
                $(byText("Редактировать"))
                        .click());
    }
}
