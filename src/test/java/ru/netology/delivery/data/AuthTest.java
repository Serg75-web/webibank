package ru.netology.delivery.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;
import static ru.netology.delivery.data.UserGenerator.Registration.getRegisteredUser;
import static ru.netology.delivery.data.UserGenerator.Registration.getUser;
import static ru.netology.delivery.data.UserGenerator.getRandomLogin;
import static ru.netology.delivery.data.UserGenerator.getRandomPassword;

class AuthTest {

        @BeforeEach
        void setup() {
            open("http://localhost:9999");
        }

        @Test
        @DisplayName("Should successfully login with active registered user")
        void shouldSuccessfulLoginIfRegisteredActiveUser() {
            var registeredUser = getRegisteredUser("active");

            // Вводим логин
            $("[data-test-id='login'] input").setValue(registeredUser.getLogin());

            // Вводим пароль
            $("[data-test-id='password'] input").setValue(registeredUser.getPassword());

            // Нажимаем кнопку "Продолжить"
            $("button .button__text").click();

            // Проверяем, что попали на страницу личного кабинета
            $("h2.heading").shouldHave(Condition.text("Личный кабинет")).shouldBe(Condition.visible);
        }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");

        // Вводим логин
        $("[data-test-id='login'] input").setValue(notRegisteredUser.getLogin());

        // Вводим пароль
        $("[data-test-id='password'] input").setValue(notRegisteredUser.getPassword());

        // Нажимаем кнопку "Продолжить"
        $("button .button__text").click();

        // Проверяем, что попали на страницу личного кабинета
        $("[data-test-id='error-notification'] .notification__content").
                shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль "))
                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        // Получаем данные заблокированного пользователя
        var blockedUser = getRegisteredUser("blocked");

        // Вводим логин
        $("[data-test-id='login'] input").setValue(blockedUser.getLogin());

        // Вводим пароль
        $("[data-test-id='password'] input").setValue(blockedUser.getPassword());

        // Нажимаем кнопку "Продолжить"
        $("[data-test-id='action-login']").click();

        // Проверяем, что отображается сообщение об ошибке
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Пользователь заблокирован"))
                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();

        // Вводим неверный логин
        $("[data-test-id='login'] input").setValue(wrongLogin);

        // Вводим пароль зарегистрированного пользователя
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());

        // Нажимаем кнопку "Продолжить"
        $("[data-test-id='action-login']").click();

        // Проверяем, что отображается сообщение об ошибке
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();

        // Вводим логин зарегистрированного пользователя
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());

        // Вводим неверный пароль
        $("[data-test-id='password'] input").setValue(wrongPassword);

        // Нажимаем кнопку "Продолжить"
        $("[data-test-id='action-login']").click();

        // Проверяем, что отображается сообщение об ошибке
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }


}
