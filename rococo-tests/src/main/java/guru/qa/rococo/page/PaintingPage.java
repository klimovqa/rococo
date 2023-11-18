package guru.qa.rococo.page;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PaintingPage extends BasePage {

    public void openPage() {
        step("Открываем страницу Картины", () -> {
            open(CFG.rococoFrontUrl() + "/painting");
            $(byText("Картины")).shouldBe(visible);
        });
    }


    public void checkTitlePainting() {
        step("Проверяем что отображается title - Картины", () ->
                $(".text-3xl.m-4").shouldBe(text("Картины")));
    }

    public void checkCountPaintings(int count) {
        step("Проверяем количество отображаемых Картин, должно быть - " + count, () -> {
            if ($$(".w-100 li").size() != count) {
                searchClick();
            }
            $$(".w-100 li").shouldHave(size(count));
        });
    }

    public void inputSearch(String name) {
        step("Вводим название картины - " + name, () ->
                $("input.input").val(name));
    }

    public void searchClick() {
        step("Нажимаем на поиск", () ->
                $("button img[alt='Иконка поиска']").click());
    }

    public void clickPaintingCard(String name) {
        step("Нажать на художника - " + name, () ->
                $("img[alt='" + name + "']").click());
    }

    public void checkPaintingNameOfPaintingCard(String name) {
        step("Проверить что отображается картинка у картины - " + name, () ->
                $("img[alt='" + name + "']").shouldBe(visible));
    }

    public void checkNameOfPaintingCard(String museumName) {
        step("Проверяем что в карточке отображается - " + museumName, () ->
                $("header.card-header").shouldBe(text(museumName)));
    }

    public void checkArtist(String artist) {
        step("Проверяем что в карточке отображается художник - " + artist, () ->
                $("article div.text-center").shouldBe(text(artist)));
    }

    public void checkDescription(String description) {
        step("Проверяем что в карточке отображается описание", () ->
                $(byText(description)).shouldBe(visible));
    }
}
