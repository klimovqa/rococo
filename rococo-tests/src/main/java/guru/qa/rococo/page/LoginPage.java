package guru.qa.rococo.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    SelenideElement username = $("input[name='username']");
    SelenideElement password = $("input[name='password']");
    SelenideElement rePassword = $("input[name='passwordSubmit']");
    SelenideElement register = $(byText("Зарегистрироваться"));

    @Step("Нажимаем кнопку Войти на форме регистрации нового пользователя")
    public LoginPage signIn() {
        signIn.click();
        return this;
    }

    @Step("Вводим имя пользователя - {name}")
    public LoginPage enterUsername(String name) {
        username.setValue(name);
        return this;
    }

    @Step("Вводим пароль - {pass}")
    public LoginPage enterPassword(String pass) {
        password.setValue(pass);
        return this;
    }

    @Step("Вводим повторно пароль - {pass}")
    public LoginPage enterRePassword(String pass) {
        rePassword.setValue(pass);
        return this;
    }

    @Step("Нажимаем Войти")
    public MainPage clickEnterButton() {
        signIn.click();
        return new MainPage();
    }

    @Step("Нажимаем Войти")
    public LoginPage clickEnterButtonFail() {
        signIn.click();
        return this;
    }

    @Step("Нажимаем Зарегистрировать")
    public LoginPage clickRegisterButtonFail() {
        register.click();
        return this;
    }

    @Step("Проверяем что отображается ошибка \"Неверные учетные данные пользователя\"")
    public LoginPage checkDisplayInvalidUserCredentials() {
        String error = "docker".equals(System.getProperty("test.env")) ? "Bad credentials" :
                "Неверные учетные данные пользователя";
        $(byText(error)).shouldBe(visible);
        return this;
    }

    @Step("Нажимаем на ссылку Зарегистрироваться")
    public LoginPage clickRegisterLink() {
        register.click();
        return this;
    }

    @Step("Нажимаем на кнопку Зарегистрироваться")
    public LoginPage clickRegisterButton() {
        register.click();
        return this;
    }

    @Step("Проверяем что появилась надпись Добро пожаловать в Rococo и кнопка Войти в систему")
    public LoginPage checkSuccessRegistered() {
        $(byText("Добро пожаловать в Ro")).shouldBe(visible);
        $(byText("coco")).shouldBe(visible);
        $(byText("Войти в систему")).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что не появилась надпись Добро пожаловать в Rococo и кнопка Войти в систему")
    public LoginPage checkDisplayNotSuccessRegistered() {
        $(byText("Добро пожаловать в Ro")).shouldBe(disappear);
        $(byText("Войти в систему")).shouldBe(disappear);
        return this;
    }

    @Step("Проверяем что появилась ошибка Passwords should be equal")
    public LoginPage checkDisplayErrorPasswordShouldBeEqual() {
        $(byText("Passwords should be equal")).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что появилась ошибка Username {username} already exists")
    public LoginPage checkDisplayErrorNotUniqueUsername(String username) {
        $(byText("Username `" + username + "` already exists")).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что отображается {errorText}")
    public LoginPage checkDisplayError(String errorText) {
        $(byText(errorText)).shouldBe(visible);
        return this;
    }
}
