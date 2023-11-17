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

    public void searchClick() {
        step("Нажимаем на поиск", () ->
                $("button img[alt='Иконка поиска']").click());
    }
}
