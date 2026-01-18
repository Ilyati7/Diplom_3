package com.burgerautotest.utils;

import com.burgerautotest.models.AuthData;
import com.burgerautotest.models.UserData;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiClient {
    private static final String BASE_URL = ConfigReader.getBaseUrl();
    private static final Gson gson = new Gson();

    static {
        RestAssured.baseURI = BASE_URL;
        RestAssured.defaultParser = Parser.JSON;
    }

    public static Response registerUser(UserData user) {
        String json = gson.toJson(user);
        System.out.println("Регистрация пользователя: " + json);

        return given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post("/api/auth/register");
    }

    public static Response loginUser(AuthData authData) {
        String json = gson.toJson(authData);
        System.out.println("Авторизация: " + json);

        return given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post("/api/auth/login");
    }

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