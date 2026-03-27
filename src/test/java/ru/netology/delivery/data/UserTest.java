package ru.netology.delivery.data;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class UserTest {
    @BeforeAll
    static void setUpAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9999;
    }

    @Test
    void shouldCreateUserSuccessfully() { //создаем пользователя
        UserGenerator.RegistrationDto user = UserGenerator.Registration.getRegisteredUser("active");

        System.out.println("Created user: " + user.getLogin());

    }

}
