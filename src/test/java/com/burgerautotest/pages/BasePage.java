package com.burgerautotest.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ожидание видимости элемента: {locator}")
    protected void waitForElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Элемент не найден: " + locator);
            throw e;
        }
    }

    @Step("Ожидание кликабельности элемента: {locator}")
    protected void waitForElementClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.out.println("Элемент не кликабелен: " + locator);
            throw e;
        }
    }

    @Step("Клик по элементу: {locator}")
    protected void clickElement(By locator) {
        waitForElementClickable(locator);
        try {
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            // Альтернативный способ клика через JavaScript
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    @Step("Ввод текста '{text}' в элемент: {locator}")
    protected void enterText(By locator, String text) {
        waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    @Step("Получение текста элемента: {locator}")
    protected String getElementText(By locator) {
        waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }

    @Step("Проверка отображения элемента: {locator}")
    protected boolean isElementDisplayed(By locator) {
        try {
            waitForElementVisible(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}