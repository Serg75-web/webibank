package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Locale;

public class UserGenerator {
    private static Faker faker = new Faker(new Locale("ru"));

    private UserGenerator() {
    }

    public static UserInfo generateUser() {
        String login = faker.name().username(); // Генерация логина
        String password = faker.internet().password(); // Генерация пароля
        String status = "active"; // Статус пользователя

        // Создаем JSON-объект для отправки
        String jsonBody = String.format(
                "{\"login\": \"%s\", \"password\": \"%s\", \"status\": \"%s\"}",
                login, password, status
        );

        // Отправляем POST-запрос на создание пользователя
        Response response = RestAssured.given()
                .baseUri("http://localhost")
                .port(9999)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonBody)
                .log().all()
                .post("/api/system/users");

        // Проверяем статус ответа
        if (response.statusCode() == 200) {
            return new UserInfo(login, password, status);
        } else {
            throw new RuntimeException("Failed to create user: " + response.body().asString());
        }
    }

    public static class UserInfo {
        String login;
        String password;
        String status;

        public UserInfo(String login, String password, String status) {
            this.login = login;
            this.password = password;
            this.status = status;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getStatus() {
            return status;
        }
    }
}
