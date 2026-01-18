package com.burgerautotest.tests;

import com.burgerautotest.models.AuthData;
import com.burgerautotest.models.UserData;
import com.burgerautotest.pages.ForgotPasswordPage;
import com.burgerautotest.pages.LoginPage;
import com.burgerautotest.pages.MainPage;
import com.burgerautotest.pages.RegistrationPage;
import com.burgerautotest.utils.ApiClient;
import com.burgerautotest.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты авторизации пользователя")
public class LoginTests extends BaseTest {

    private UserData testUser;
    private String authToken;

    @Before
    public void setUpTestUser() {
        // Создаем пользователя через API перед каждым тестом
        testUser = new UserData(
                TestDataGenerator.generateRandomEmail(),
                TestDataGenerator.generateValidPassword(),
                TestDataGenerator.generateRandomName()
        );

        Response registerResponse = ApiClient.registerUser(testUser);
        System.out.println("Статус регистрации пользователя: " + registerResponse.getStatusCode());
    }

    @After
    public void tearDownTestUser() {
        // Логинимся, чтобы получить токен для удаления
        AuthData authData = new AuthData(testUser.getEmail(), testUser.getPassword());
        Response loginResponse = ApiClient.loginUser(authData);

        if (loginResponse.getStatusCode() == 200) {
            authToken = ApiClient.extractToken(loginResponse);
            if (authToken != null) {
                // Удаляем пользователя через API после каждого теста
                Response deleteResponse = ApiClient.deleteUser(authToken);
                System.out.println("Статус удаления пользователя: " +
                        (deleteResponse != null ? deleteResponse.getStatusCode() : "не удалось удалить"));
            }
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Проверка входа через кнопку на главной странице")
    public void testLoginViaMainPageButton() {
        System.out.println("Тест: вход через кнопку на главной странице");

        // Вход через главную страницу
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickLoginAccountButton();

        // Вход
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Проверяем, что вернулись на главную страницу
        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());

        System.out.println("✅ Вход через главную страницу успешен");
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку личного кабинета")
    public void testLoginViaPersonalAccountButton() {
        System.out.println("Тест: вход через кнопку 'Личный кабинет'");

        // Вход через кнопку личного кабинета
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickPersonalAccountButton();

        // Вход
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Проверяем, что вернулись на главную страницу
        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());

        System.out.println("✅ Вход через личный кабинет успешен");
    }

    @Test
    @DisplayName("Вход через страницу регистрации")
    @Description("Проверка входа через ссылку на странице регистрации")
    public void testLoginViaRegistrationPage() {
        System.out.println("Тест: вход через страницу регистрации");

        // Переход на страницу входа через страницу регистрации
        driver.get(baseUrl + "/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForLoad();
        registrationPage.clickLoginLink();

        // Проверяем, что перешли на страницу входа
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должна отображаться страница входа",
                loginPage.isLoginPage());

        // Выполняем вход
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Проверяем, что вернулись на главную страницу
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());

        System.out.println("✅ Вход через страницу регистрации успешен");
    }

    @Test
    @DisplayName("Вход через страницу восстановления пароля")
    @Description("Проверка входа через ссылку на странице восстановления пароля")
    public void testLoginViaForgotPasswordPage() {
        System.out.println("Тест: вход через страницу восстановления пароля");

        // Переход на страницу восстановления пароля
        driver.get(baseUrl + "/forgot-password");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.waitForLoad();
        forgotPasswordPage.clickLoginLink();

        // Проверяем, что перешли на страницу входа
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должна отображаться страница входа",
                loginPage.isLoginPage());

        // Выполняем вход
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Проверяем, что вернулись на главную страницу
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());

        System.out.println("✅ Вход через страницу восстановления пароля успешен");
    }
}