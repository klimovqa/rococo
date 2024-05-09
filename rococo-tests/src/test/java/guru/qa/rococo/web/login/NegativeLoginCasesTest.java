package guru.qa.rococo.web.login;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.jupiter.annotation.CreateUser;
import guru.qa.rococo.page.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


@Epic("[WEB]")
@Feature("Авторизация")
@Story("Неуспешные сценарии входа в личный кабинет")
@DisplayName("[WEB] Неуспешные сценарии входа в личный кабинет")
@Tag("WEB")
public class NegativeLoginCasesTest extends BaseTest {
    private final static String USERNAME_NOT_UNIQ = "igor777";
    private final static String PASSWORD = "12345";

    @DisplayName("Вход под НЕ зарегистрированным пользователем")
    @Test
    void signInNotRegisteredUserTest() {
        new MainPage().openMainPage()
                .goToLogin()
                .enterUsername("NOT_REGISTERED_USER")
                .enterPassword(PASSWORD)
                .clickEnterButtonFail()
                .checkDisplayInvalidUserCredentials();
    }

    @DisplayName("Регистрация пользователя (пароли не совпадают)")
    @Test
    void userRegistrationPasswordDontMatchTest() {
        new MainPage().openMainPage()
                .goToLogin()
                .clickRegisterLink()
                .enterUsername("USER_REG_PASSWORD_DONT_MATCH")
                .enterPassword("12345")
                .enterRePassword("123456")
                .clickRegisterButtonFail()
                .checkDisplayErrorPasswordShouldBeEqual();
    }

    private static Stream<Arguments> provideErrorRegistration() {
        return Stream.of(
                Arguments.of("USER_REG_PASSWORD_LENGTH", "12", "Allowed password length should be from 3 to 12 characters"),
                Arguments.of("USER_REG_PASSWORD_LENGTH2", "1234567890123", "Allowed password length should be from 3 to 12 characters"));
    }

    @ParameterizedTest(name = "Пользователь должен получить сообщение об ошибке : {2} при вводе некорректных данных")
    @MethodSource("provideErrorRegistration")
    void userRegistrationPasswordLengthTest(String login, String password, String error) {
        new MainPage().openMainPage()
                .goToLogin()
                .clickRegisterLink()
                .enterUsername(login)
                .enterPassword(password)
                .enterRePassword(password)
                .clickRegisterButtonFail()
                .checkDisplayError(error);
    }

    @DisplayName("Регистрация пользователя (пароль не введен повторно)")
    @Test
    void userRegistrationPasswordWastEnteredAgainTest() {
        new MainPage().openMainPage()
                .goToLogin()
                .clickRegisterLink()
                .enterUsername("USER_REG_PASSWORD_WAS_NOT_ENTERED_AGAIN")
                .enterPassword("12345")
                .clickRegisterButtonFail()
                .checkDisplayNotSuccessRegistered();
    }

    @DisplayName("Регистрация пользователя (пользователь с таким логином существует)")
    @CreateUser(username = USERNAME_NOT_UNIQ,
            password = PASSWORD)
    @Test
    void userRegistrationUserWithThisUsernameExistsTest() {
        new MainPage().openMainPage()
                .goToLogin()
                .clickRegisterLink()
                .enterUsername(USERNAME_NOT_UNIQ)
                .enterPassword(PASSWORD)
                .enterRePassword(PASSWORD)
                .clickRegisterButtonFail()
                .checkDisplayErrorNotUniqueUsername(USERNAME_NOT_UNIQ);
    }
}
