package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class UserPage extends BasePage {

    SelenideElement exit = $(byText("Выйти"));
    SelenideElement firstName = $("input[name='firstname']");
    SelenideElement surname = $("input[name='surname']");
    SelenideElement updateProfile = $(byText("Обновить профиль"));

    @Step("Загружаем аватар пользователю")
    public UserPage uploadAvatar() {
        file.shouldBe(visible)
                .uploadFromClasspath("photo/avatar/avatar.png");
        return this;
    }

    @Step("Заполняем имя - {val}")
    public UserPage enterFirstname(String val) {
        firstName.setValue(val);
        return this;
    }

    @Step("Заполняем имя - {val}")
    public UserPage enterSurname(String val) {
        surname.setValue(val);
        return this;
    }

    @Step("Нажимаем обновить профиль")
    public MainPage clickProfileUpdateButton() {
        updateProfile.click();
        return new MainPage();
    }

    @Step("Нажимаем на кнопку Выйти")
    public MainPage clickLogout() {
        exit.click();
        return new MainPage();
    }
}
