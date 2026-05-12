package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ForgotPasswordPage extends BasePage {

    private final By loginLink = By.xpath("//a[contains(text(), 'Войти')]");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        clickElement(loginLink);
    }

    @Step("Проверка, что открыта страница восстановления пароля")
    public boolean isForgotPasswordPage() {
        return isElementDisplayed(loginLink);
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForLoad() {
        waitForElementVisible(loginLink);
    }
}