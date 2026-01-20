package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;

public class MainPage extends BasePage {

    private final By loginAccountButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private final By personalAccountButton = By.xpath("//a[contains(@href, 'account') or contains(text(), 'Личный Кабинет')]");
    private final By constructorTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");

    private final By bunsTab = By.xpath("//span[contains(text(), 'Булки')]/parent::div");
    private final By saucesTab = By.xpath("//span[contains(text(), 'Соусы')]/parent::div");
    private final By fillingsTab = By.xpath("//span[contains(text(), 'Начинки')]/parent::div");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        clickElement(loginAccountButton);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        clickElement(personalAccountButton);
    }

    @Step("Клик по разделу 'Булки'")
    public void clickBunsTab() {
        clickElement(bunsTab);
    }

    @Step("Клик по разделу 'Соусы'")
    public void clickSaucesTab() {
        clickElement(saucesTab);
    }

    @Step("Клик по разделу 'Начинки'")
    public void clickFillingsTab() {
        clickElement(fillingsTab);
    }

    @Step("Проверка отображения конструктора")
    public boolean isConstructorDisplayed() {
        return isElementDisplayed(constructorTitle);
    }

    @Step("Проверка активности раздела 'Булки'")
    public boolean isBunsSectionActive() {
        try {
            return driver.findElement(bunsTab).getAttribute("class").contains("current") ||
                    driver.findElement(bunsTab).getAttribute("class").contains("active");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Соусы'")
    public boolean isSaucesSectionActive() {
        try {
            return driver.findElement(saucesTab).getAttribute("class").contains("current") ||
                    driver.findElement(saucesTab).getAttribute("class").contains("active");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Начинки'")
    public boolean isFillingsSectionActive() {
        try {
            return driver.findElement(fillingsTab).getAttribute("class").contains("current") ||
                    driver.findElement(fillingsTab).getAttribute("class").contains("active");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoad() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(loginAccountButton),
                ExpectedConditions.visibilityOfElementLocated(constructorTitle),
                ExpectedConditions.visibilityOfElementLocated(bunsTab)
        ));
    }

    @Step("Ожидание активности раздела 'Булки'")
    public void waitForBunsSectionActive() {
        wait.until(driver -> isBunsSectionActive());
    }

    @Step("Ожидание активности раздела 'Соусы'")
    public void waitForSaucesSectionActive() {
        wait.until(driver -> isSaucesSectionActive());
    }

    @Step("Ожидание активности раздела 'Начинки'")
    public void waitForFillingsSectionActive() {
        wait.until(driver -> isFillingsSectionActive());
    }
}