package guru.qa.rococo.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.rococo.config.Config;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected static final Config CFG = Config.getInstance();

    protected SelenideElement file = $("input[type='file'][name]");
    protected SelenideElement addButton = $(byText("Добавить"));
    protected SelenideElement editButton = $(byText("Редактировать"));
    protected SelenideElement saveButton = $(byText("Сохранить"));
    protected SelenideElement inputSearch = $("input.input");
    protected SelenideElement searchButton = $("button img[alt='Иконка поиска']");
    protected SelenideElement signIn = $(byText("Войти"));
    protected SelenideElement mainPageLink = $("a[href='/'");
    protected ElementsCollection resultOfSearch = $$(".w-100 li");

    @Step("Переходим на главную страницу")
    public MainPage goToMainPage() {
        mainPageLink.click();
        return new MainPage();
    }
}

