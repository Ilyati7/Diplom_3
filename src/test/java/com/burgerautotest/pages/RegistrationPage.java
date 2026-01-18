package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    // Локаторы для страницы регистрации
    private By nameField = By.xpath("(//input[@name='name'])[1]"); // Первое поле name
    private By emailField = By.xpath("(//input[@name='name'])[2]"); // Второе поле name (для email)
    private By passwordField = By.xpath("//input[@type='password']");
    private By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private By loginLink = By.xpath("//a[contains(text(), 'Войти')]");
    private By passwordError = By.xpath("//p[contains(@class, 'error') or contains(text(), 'Некорректный пароль') or contains(text(), 'password')]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        enterText(nameField, name);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickRegisterButton() {
        clickElement(registerButton);
    }

    public void clickLoginLink() {
        clickElement(loginLink);
    }

    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public boolean isPasswordErrorDisplayed() {
        return isElementDisplayed(passwordError);
    }

    public boolean isRegistrationPage() {
        return isElementDisplayed(registerButton);
    }

    public void waitForLoad() {
        waitForElementVisible(registerButton);
    }
}