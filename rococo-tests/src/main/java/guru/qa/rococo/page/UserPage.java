package guru.qa.rococo.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class UserPage extends BasePage{

    public void uploadAvatar() {
        step("Загружаем аватар юзеру", () ->
                $("input[type='file'][name]")
                        .uploadFromClasspath("photo/avatar/avatar.png"));
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
