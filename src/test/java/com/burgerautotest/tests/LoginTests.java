package com.burgerautotest.tests;

import com.burgerautotest.pages.ForgotPasswordPage;
import com.burgerautotest.pages.LoginPage;
import com.burgerautotest.pages.MainPage;
import com.burgerautotest.pages.RegistrationPage;
import com.burgerautotest.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты авторизации пользователя")
public class LoginTests extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Проверка входа через кнопку на главной странице")
    public void testLoginViaMainPageButton() {
        System.out.println("Тест: вход через кнопку на главной странице");

        try {
            // Создаем пользователя через UI
            String email = TestDataGenerator.generateRandomEmail();
            String password = TestDataGenerator.generateValidPassword();
            String name = TestDataGenerator.generateRandomName();

            // Регистрация нового пользователя
            driver.get(baseUrl + "/register");
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();
            registrationPage.register(name, email, password);

            // Ждем перехода на страницу входа
            Thread.sleep(2000);

            // Вход через главную страницу
            driver.get(baseUrl);
            MainPage mainPage = new MainPage(driver);
            mainPage.waitForLoad();
            mainPage.clickLoginAccountButton();

            // Вход
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoad();
            loginPage.login(email, password);

            // Проверяем, что вернулись на главную страницу
            Thread.sleep(2000);
            assertTrue("Должна отображаться главная страница после входа",
                    mainPage.isConstructorDisplayed());

            System.out.println("✅ Вход через главную страницу успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при входе через главную страницу: " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку личного кабинета")
    public void testLoginViaPersonalAccountButton() {
        System.out.println("Тест: вход через кнопку 'Личный кабинет'");

        try {
            // Создаем пользователя через UI
            String email = TestDataGenerator.generateRandomEmail();
            String password = TestDataGenerator.generateValidPassword();
            String name = TestDataGenerator.generateRandomName();

            // Регистрация нового пользователя
            driver.get(baseUrl + "/register");
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();
            registrationPage.register(name, email, password);

            // Ждем перехода на страницу входа
            Thread.sleep(2000);

            // Вход через кнопку личного кабинета
            driver.get(baseUrl);
            MainPage mainPage = new MainPage(driver);
            mainPage.waitForLoad();
            mainPage.clickPersonalAccountButton();

            // Вход
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoad();
            loginPage.login(email, password);

            // Проверяем, что вернулись на главную страницу
            Thread.sleep(2000);
            assertTrue("Должна отображаться главная страница после входа",
                    mainPage.isConstructorDisplayed());

            System.out.println("✅ Вход через личный кабинет успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при входе через личный кабинет: " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Вход через страницу регистрации")
    @Description("Проверка входа через ссылку на странице регистрации")
    public void testLoginViaRegistrationPage() {
        System.out.println("Тест: вход через страницу регистрации");

        try {
            // Создаем пользователя через UI
            String email = TestDataGenerator.generateRandomEmail();
            String password = TestDataGenerator.generateValidPassword();
            String name = TestDataGenerator.generateRandomName();

            // Регистрация нового пользователя
            driver.get(baseUrl + "/register");
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();
            registrationPage.register(name, email, password);

            // Ждем перехода на страницу входа
            Thread.sleep(2000);

            // Переход на страницу входа через страницу регистрации
            driver.get(baseUrl + "/register");
            registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();
            registrationPage.clickLoginLink();

            // Проверяем, что перешли на страницу входа
            LoginPage loginPage = new LoginPage(driver);
            assertTrue("Должна отображаться страница входа",
                    loginPage.isLoginPage());

            // Выполняем вход
            loginPage.login(email, password);

            // Проверяем, что вернулись на главную страницу
            Thread.sleep(2000);
            MainPage mainPage = new MainPage(driver);
            assertTrue("Должна отображаться главная страница после входа",
                    mainPage.isConstructorDisplayed());

            System.out.println("✅ Вход через страницу регистрации успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при входе через страницу регистрации: " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Вход через страницу восстановления пароля")
    @Description("Проверка входа через ссылку на странице восстановления пароля")
    public void testLoginViaForgotPasswordPage() {
        System.out.println("Тест: вход через страницу восстановления пароля");

        try {
            // Создаем пользователя через UI
            String email = TestDataGenerator.generateRandomEmail();
            String password = TestDataGenerator.generateValidPassword();
            String name = TestDataGenerator.generateRandomName();

            // Регистрация нового пользователя
            driver.get(baseUrl + "/register");
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();
            registrationPage.register(name, email, password);

            // Ждем перехода на страницу входа
            Thread.sleep(2000);

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
            loginPage.login(email, password);

            // Проверяем, что вернулись на главную страницу
            Thread.sleep(2000);
            MainPage mainPage = new MainPage(driver);
            assertTrue("Должна отображаться главная страница после входа",
                    mainPage.isConstructorDisplayed());

            System.out.println("✅ Вход через страницу восстановления пароля успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при входе через страницу восстановления пароля: " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }
}
