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

    @Before
    public void setUpTestUser() {
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
        AuthData authData = new AuthData(testUser.getEmail(), testUser.getPassword());
        Response loginResponse = ApiClient.loginUser(authData);

        if (loginResponse.getStatusCode() == 200) {
            String authToken = ApiClient.extractToken(loginResponse);
            if (authToken != null) {
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
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку личного кабинета")
    public void testLoginViaPersonalAccountButton() {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через страницу регистрации")
    @Description("Проверка входа через ссылку на странице регистрации")
    public void testLoginViaRegistrationPage() {
        driver.get(baseUrl + "/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForLoad();
        registrationPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должна отображаться страница входа",
                loginPage.isLoginPage());

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через страницу восстановления пароля")
    @Description("Проверка входа через ссылку на странице восстановления пароля")
    public void testLoginViaForgotPasswordPage() {
        driver.get(baseUrl + "/forgot-password");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.waitForLoad();
        forgotPasswordPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Должна отображаться страница входа",
                loginPage.isLoginPage());

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        assertTrue("Должна отображаться главная страница после входа",
                mainPage.isConstructorDisplayed());
    }
}