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

    public void checkCountArtists(int count) {
        step("Проверяем количество отображаемых Художников, должно быть - " + count, () ->
                $$(".w-100 li").shouldHave(size(count)));
    }

    public void inputSearch(String name) {
        step("Вводим название художника - " + name, () ->
                $("input.input").val(name));
    }

    public void searchClick() {
        step("Нажимаем на поиск", () ->
                $("button img[alt='Иконка поиска']").click());
    }


    public void clickArtistCard(String name) {
        step("Нажать на художника - " + name, () ->
                $(byText(name)).click());
    }

    public void checkArtistNameOfArtistCard(String name) {
        step("Проверить что отображается картинка у художника - " + name, () ->
                $("section img").shouldBe(visible));
    }

    public void checkNameOfArtistCard(String artistName) {
        step("Проверяем что в карточке отображается - " + artistName, () ->
                $("header.card-header").shouldBe(text(artistName)));
    }

    public void checkDescription(String description) {
        step("Проверяем что в карточке отображается описание", () ->
                $(byText(description)).shouldBe(visible));
    }
}
