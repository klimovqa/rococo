package guru.qa.rococo.page;

import java.io.File;
import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class LoginPage extends BasePage {

    public void signIn() {
        step("Нажимаем кнопку Войти на форме регистрации пользователя", () ->
                $(byText("Войти")).click());
    }

    public void signInSystem() {
        step("Нажимаем кнопку Войти в систему", () ->
                $(byText("Войти в систему")).click());
    }

    public void inputUsername(String username) {
        step("Вводим имя пользователя - " + username, () ->
                $("input[name='username']").setValue(username));
    }

    public void inputPassword(String password) {
        step("Вводим пароль - " + password, () ->
                $("input[name='password']").setValue(password));
    }

    public void inputPasswordSubmit(String password) {
        step("Вводим повторно пароль - " + password, () ->
                $("input[name='passwordSubmit']").setValue(password));
    }

    public void clickSubmit() {
        step("Нажимаем Войти", () ->
                $(".form__submit").click());
    }

    public void checkAvatarExist() {
        step("Проверяем что появилась аватарка", () ->
                $(".avatar-initials").shouldBe(exist));
    }

    public void checkInvalidUserCredentials() {
        step("Проверяем что отображается ошибка \"Неверные учетные данные пользователя\"", () ->
                $(byText("Неверные учетные данные пользователя")).shouldBe(visible));
    }

    public void registerClick() {
        step("Нажимаем на ссылку Зарегистрироваться", () ->
                $(byText("Зарегистрироваться")).click());
    }

    public void checkSuccessRegistered() {
        step("Проверяем что появилась надпись Добро пожаловать " +
                "в Rococo и кнопка Войти в систему", () -> {
            $(byText("Добро пожаловать в Ro")).shouldBe(visible);
            $(byText("coco")).shouldBe(visible);
            $(byText("Войти в систему")).shouldBe(visible);
        });
    }
    public void checkNotSuccessRegistered() {
        step("Проверяем что не появилась надпись Добро пожаловать " +
                "в Rococo и кнопка Войти в систему", () -> {
            $(byText("Добро пожаловать в Ro")).shouldBe(disappear);
            $(byText("Войти в систему")).shouldBe(disappear);
        });
    }

    public void checkErrorPasswordShouldBeEqual() {
        step("Проверяем что появилась ошибка Passwords should be equal", () ->
                $(byText("Passwords should be equal")).shouldBe(visible));
    }

    public void checkErrorNotUniqueUsername(String username) {
        step("Проверяем что появилась ошибка Username `" + username + "` already exists", () ->
                $(byText("Username `" + username + "` already exists")).shouldBe(visible));
    }

    public void clickAvatar(String username) {
        step("Нажимаем на аватар и ждем пока появится МО с профилем", () -> {
                $("figure[data-testid]").shouldBe(visible).click();
                if (!$(byText(username)).isDisplayed()) {
                    $("figure[data-testid]").shouldBe(visible).click();
                }
        });
    }

    public void clickLogout() {
        step("Нажимаем на кнопку Выйти", () ->
                $(byText("Выйти")).click());
    }

    public void checkSessionIsOver() {
        step("Проверяем что отображается тостер Сессия завершена", () ->
                $(byText("Сессия завершена")).shouldBe(visible));
    }

    public void checkError(String errorText) {
        step("Проверяем что отображается " + errorText, () ->
                $(byText(errorText)).shouldBe(visible));
    }

    public void uploadAvatar() {
        step("Загружаем аватар юзеру", () ->
            $("input[type='file'][name]")
                .uploadFromClasspath("avatar/avatar.png"));
    }

    public void fillFirstname(String firstname) {
        step("Заполняем имя - " + firstname, () ->
                $("input[name='firstname']").setValue(firstname));
    }

    public void fillSurname(String surname) {
        step("Заполняем фамилию - " + surname, () ->
                $("input[name='surname']").setValue(surname));
    }

    public void updateProfileClick() {
        step("Нажимаем обновить профиль", () ->
                $(byText("Обновить профиль")).click());
    }

    public void checkUpdateProfile() {
        step("Проверяем что появился тостер Профиль обновлен", () ->
                $(byText("Профиль обновлен")).shouldBe(visible));
    }
    public void checkAvatarImage() {
        step("Проверяем что аватарка загружена", () ->
                $(".avatar-image").shouldBe(visible));
    }
}
