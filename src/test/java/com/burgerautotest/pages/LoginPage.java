package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Локаторы для страницы входа
    private By emailField = By.xpath("//input[@name='name']"); // Поле для email
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[contains(text(), 'Войти')]");
    private By registerLink = By.xpath("//a[contains(text(), 'Зарегистрироваться')]");
    private By forgotPasswordLink = By.xpath("//a[contains(text(), 'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void clickRegisterLink() {
        clickElement(registerLink);
    }

    public void clickForgotPasswordLink() {
        clickElement(forgotPasswordLink);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginPage() {
        return isElementDisplayed(loginButton);
    }

    public void waitForLoad() {
        waitForElementVisible(loginButton);
    }
}