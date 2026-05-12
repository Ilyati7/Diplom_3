package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class RegistrationPage extends BasePage {

    private final By nameField = By.xpath("(//input[@name='name'])[1]");
    private final By emailField = By.xpath("(//input[@name='name'])[2]");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private final By loginLink = By.xpath("//a[contains(text(), 'Войти')]");
    private final By passwordError = By.xpath("//p[contains(@class, 'error') or contains(text(), 'Некорректный пароль')]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        enterText(nameField, name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        clickElement(registerButton);
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        clickElement(loginLink);
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        return isElementDisplayed(passwordError);
    }

    @Step("Проверка, что открыта страница регистрации")
    public boolean isRegistrationPage() {
        return isElementDisplayed(registerButton);
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForLoad() {
        waitForElementVisible(registerButton);
    }
}