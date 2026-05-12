package com.burgerautotest.utils;

import com.burgerautotest.models.AuthData;
import com.burgerautotest.models.UserData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiClient {
    private static final String BASE_URL = ConfigReader.getBaseUrl();

    static {
        RestAssured.baseURI = BASE_URL;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Step("Регистрация пользователя через API")
    public static Response registerUser(UserData user) {
        System.out.println("Регистрация пользователя: " + user);

        return given()
                .header("Content-type", "application/json")
                .body(user)  // ✅ RestAssured автоматически сериализует объект в JSON
                .when()
                .post("/api/auth/register");
    }

    @Step("Авторизация пользователя через API")
    public static Response loginUser(AuthData authData) {
        System.out.println("Авторизация: " + authData);

        return given()
                .header("Content-type", "application/json")
                .body(authData)  // ✅ RestAssured автоматически сериализует объект в JSON
                .when()
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя через API")
    public static Response deleteUser(String token) {
        if (token == null || token.isEmpty()) {
            System.out.println("Токен пустой, удаление невозможно");
            return null;
        }

        return given()
                .header("Authorization", token)
                .when()
                .delete("/api/auth/user");
    }

    @Step("Извлечение токена из ответа")
    public static String extractToken(Response response) {
        try {
            if (response.getStatusCode() == 200) {
                String token = response.then().extract().path("accessToken");
                System.out.println("Токен получен: " + (token != null ? "да" : "нет"));
                return token;
            } else {
                System.out.println("Ошибка API: " + response.getStatusCode());
                System.out.println("Ответ: " + response.getBody().asString());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при извлечении токена: " + e.getMessage());
            return null;
        }
    }
}