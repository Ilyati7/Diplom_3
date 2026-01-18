package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private By loginLink = By.xpath("//a[contains(text(), 'Войти')]");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginLink() {
        clickElement(loginLink);
    }

    public boolean isForgotPasswordPage() {
        return isElementDisplayed(loginLink);
    }

    public void waitForLoad() {
        waitForElementVisible(loginLink);
    }
}