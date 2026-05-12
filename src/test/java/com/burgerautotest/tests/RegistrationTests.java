package com.burgerautotest.tests;

import com.burgerautotest.pages.LoginPage;
import com.burgerautotest.pages.RegistrationPage;
import com.burgerautotest.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты регистрации пользователя")
public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка успешной регистрации с валидными данными")
    public void testSuccessfulRegistration() {
        String email = TestDataGenerator.generateRandomEmail();
        String password = TestDataGenerator.generateValidPassword();
        String name = TestDataGenerator.generateRandomName();

        System.out.println("Тест регистрации с данными:");
        System.out.println("Имя: " + name);
        System.out.println("Email: " + email);
        System.out.println("Пароль: " + password);

        try {
            driver.get(baseUrl + "/register");
            System.out.println("URL: " + driver.getCurrentUrl());

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();

            registrationPage.register(name, email, password);

            // Ждем перехода на страницу входа
            Thread.sleep(2000);

            LoginPage loginPage = new LoginPage(driver);
            assertTrue("Должна отображаться страница входа после регистрации",
                    loginPage.isLoginPage());

            System.out.println("✅ Регистрация прошла успешно");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при регистрации: " + e.getMessage());
            System.out.println("Текущий URL: " + driver.getCurrentUrl());
            throw new AssertionError("Тест регистрации не прошел: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    @Description("Проверка отображения ошибки при пароле менее 6 символов")
    public void testRegistrationWithShortPassword() {
        String email = TestDataGenerator.generateRandomEmail();
        String password = TestDataGenerator.generateShortPassword(); // "123"
        String name = TestDataGenerator.generateRandomName();

        System.out.println("Тест регистрации с коротким паролем:");
        System.out.println("Пароль: " + password + " (длина: " + password.length() + ")");

        try {
            driver.get(baseUrl + "/register");

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.waitForLoad();

            registrationPage.register(name, email, password);

            // Даем время для появления ошибки
            Thread.sleep(2000);

            boolean isErrorDisplayed = registrationPage.isPasswordErrorDisplayed();
            assertTrue("Должна отображаться ошибка при коротком пароле", isErrorDisplayed);

            System.out.println("✅ Ошибка при коротком пароле отображается корректно");

        } catch (Exception e) {
            System.out.println("❌ Ошибка в тесте короткого пароля: " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }
}