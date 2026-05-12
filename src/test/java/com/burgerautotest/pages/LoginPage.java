package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[contains(text(), 'Войти')]");
    private final By registerLink = By.xpath("//a[contains(text(), 'Зарегистрироваться')]");
    private final By forgotPasswordLink = By.xpath("//a[contains(text(), 'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        clickElement(loginButton);
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        clickElement(registerLink);
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        clickElement(forgotPasswordLink);
    }

    @Step("Авторизация пользователя")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Проверка, что открыта страница входа")
    public boolean isLoginPage() {
        return isElementDisplayed(loginButton);
    }

    @Step("Ожидание загрузки страницы входа")
    public void waitForLoad() {
        waitForElementVisible(loginButton);
    }
}