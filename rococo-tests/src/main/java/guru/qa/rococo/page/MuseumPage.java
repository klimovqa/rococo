package guru.qa.rococo.page;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MuseumPage extends BasePage {

    public void openPage() {
        step("Открываем страницу Музеи", () -> {
            open(CFG.rococoFrontUrl() + "/museum");
            $(byText("Музеи")).shouldBe(visible);
        });
    }

    public void checkTitleMuseum() {
        step("Проверяем что отображается title - Музеи", () ->
                $(".text-3xl.m-4").shouldBe(text("Музеи")));
    }

    public void checkCountMuseums(int count) {
        step("Проверяем количество отображаемых Музеев, должно быть - " + count, () ->
                $$(".w-100 li").shouldHave(size(count)));
    }

    public void inputSearch(String name) {
        step("Вводим название музея - " + name, () ->
                $("input.input").val(name));
    }

    public void inputMuseumName(String name) {
        step("Вводим название музея - " + name, () ->
                $("input[name='title']").val(name));
    }

    public void inputMuseumCity(String city) {
        step("Вводим название города - " + city, () ->
                $("input[name='city']").val(city));
    }

    public void inputMuseumDescription(String desc) {
        step("Вводим описание музея", () -> {
            $("textarea[name='description']").shouldBe(visible);
            $("textarea[name='description']").val(desc);
        });
    }

    public void inputMuseumCountry(String country) {
        step("Выбираем страну - " + country, () ->
                $("select.select").selectOptionContainingText(country));
    }

    public void uploadMuseumPhoto(String pathPhoto) {
        step("Загружаем фото музея", () ->
                $("input[type='file'][name]")
                        .uploadFromClasspath(pathPhoto));
    }

    public void addedMuseum() {
        step("Нажимаем кнопку Добавить", () ->
                $(byText("Добавить"))
                        .click());
    }

    public void saveMuseum() {
        step("Нажимаем кнопку Сохранить", () ->
                $(byText("Сохранить"))
                        .click());
    }

    public void searchClick() {
        step("Нажимаем на поиск", () ->
                $("button img[alt='Иконка поиска']").click());
    }

    public void clickMuseumCard(String name) {
        $("img[alt='" + name + "']").shouldBe(visible);
        step("Нажать на музей - " + name, () ->
                $("img[alt='" + name + "']").click());
    }

    public void checkMuseumNameOfMuseumCard(String name) {
        step("Проверить что отображается картинка у музея - " + name, () ->
                $("img[alt='" + name + "']").shouldBe(visible));
    }

    public void checkNameOfMuseumCard(String museumName) {
        step("Проверяем что в карточке отображается - " + museumName, () ->
                $("header.card-header").shouldBe(text(museumName)));
    }

    public void checkAddress(String address) {
        step("Проверяем что в карточке отображается адрес- " + address, () ->
                $("article div.text-center").shouldBe(text(address)));
    }

    public void checkDescription(String description) {
        step("Проверяем что в карточке отображается описание", () ->
                $(byText(description)).shouldBe(visible));
    }

    public void clickAddMuseum() {
        step("Нажимаем Добавить музей", () ->
                $(byText("Добавить музей")).click());
    }

    public void editMuseum() {
        step("Нажимаем Редактировать", () ->
                $(byText("Редактировать")).click());
    }
}
