//package ru.netology.delivery.data;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//@Nested
//class UserApiTest {
//
//    @BeforeAll
//    public static void setup() {
//        // Установите базовый URL вашего приложения
//        RestAssured.baseURI = "http://localhost:9999/"; // Замените на ваш базовый URL
//    }
//
//    @Test
//    public void testLoginWithValidCredentials() {
//        given()
//                .contentType(ContentType.JSON)
//                .body("username", equalTo(validUser), "password": "validPassword"}")
//                .when()
//                .post("/login")
//                .then()
//                .statusCode(200)
//                .body("message", equalTo("Login successful"));
//    }
//
//    @Test
//    public void testLoginWithInvalidUsername() {
//        given()
//                .contentType(ContentType.JSON)
//                .body("{"username": "invalidUser", "password": "validPassword"}")
//                .when()
//                .post("/login")
//                .then()
//                .statusCode(401)
//                .body("message", equalTo("Invalid username or password"));
//    }
//
//    @Test
//    public void testLoginWithInvalidPassword() {
//        given()
//                .contentType(ContentType.JSON)
//                .body("{"username": "validUser", "password": "invalidPassword"}")
//                .when()
//                .post("/login")
//                .then()
//                .statusCode(401)
//                .body("message", equalTo("Invalid username or password"));
//    }
//
//    @Test
//    public void testLoginWithMissingUsername() {
//        given()
//                .contentType(ContentType.JSON)
//                .body("{"password": "validPassword"}")
//                .when()
//                .post("/login")
//                .then()
//                .statusCode(400)
//                .body("message", equalTo("Username is required"));
//    }
//
//    @Test
//    public void testLoginWithMissingPassword() {
//        given()
//                .contentType(ContentType.JSON)
//                .body("{"username": "validUser"}")
//                .when()
//                .post("/login")
//                .then()
//                .statusCode(400)
//                .body("message", equalTo("Password is required"));
//    }
//}
