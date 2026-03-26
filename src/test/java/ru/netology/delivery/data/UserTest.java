package ru.netology.delivery.data;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {
    @BeforeAll
    static void setUpAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9999;
    }

    @Test
    void shouldCreateUserSuccessfully() {
        UserGenerator.UserInfo user = UserGenerator.generateUser();

        // Логируем информацию о созданном пользователе
        System.out.println("Created user: " + user.getLogin());

//        // Проверяем, что пользователь успешно создан
//        given()
//                .contentType(ContentType.JSON)
//                .body(String.format("{\"login\": \"%s\", \"password\": \"%s\"}", user.getLogin(), user.getPassword()))
//                .when()
//                .post("/api/system/users/login/")
//                .then()
//                .statusCode(200)
//                .body("message", equalTo("Login successful"))

        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200)
                .body("login", equalTo(user.getLogin()),
                        "password", equalTo(user.getPassword()));
    }

//
//    @Test
//    void shouldReturnUserStatus() {
//        UserGenerator.UserInfo user = UserGenerator.generateUser();
//
//        // Проверяем статус пользователя
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .body(String.format("{\"login\": \"%s\", \"password\": \"%s\"}", user.getLogin(), user.getPassword()))
//                .when()
//                .post("/api/system/users/login");
//
//        // Логируем статус пользователя
//        System.out.println("User status: " + user.getStatus());
//
//                .statusCode(200)
//                .body("message", equalTo("Login successful")); // Измените в зависимости от вашего API
//    }
//
//    @Test
//    void shouldFailLoginWithInvalidUsername() {
//        UserGenerator.UserInfo user = UserGenerator.generateUser();
//
//        // Проверяем вход с несуществующим именем пользователя
//        given()
//                .contentType(ContentType.JSON)
//                .body("{\"login\": \"invalidUser\", \"password\": \"\" + user.getPassword() + \"\"}")
//                .when()
//                .post("/api/system/users/login")
//                .then()
//                .statusCode(401)
//                .body("message", equalTo("Invalid username or password"));
//    }
//
//    @Test
//    void shouldFailLoginWithInvalidPassword() {
//        UserGenerator.UserInfo user = UserGenerator.generateUser();
//
//        // Проверяем вход с существующим именем пользователя, но неправильным паролем
//        given()
//                .contentType(ContentType.JSON)
//                .body(String.format("{\"login\": \"%s\", \"password\": \"invalidPassword\"}", user.getLogin()))
//                .when()
//                .post("/api/system/users/login")
//                .then()
//                .statusCode(401)
//                .body("message", equalTo("Invalid username or password"));


}
