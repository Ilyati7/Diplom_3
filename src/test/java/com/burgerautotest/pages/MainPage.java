package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private By loginAccountButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private By personalAccountButton = By.xpath("//a[contains(@href, 'account') or contains(text(), 'Личный Кабинет')]");
    private By constructorTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");

    private By bunsTab = By.xpath("//span[contains(text(), 'Булки')]/parent::div");
    private By saucesTab = By.xpath("//span[contains(text(), 'Соусы')]/parent::div");
    private By fillingsTab = By.xpath("//span[contains(text(), 'Начинки')]/parent::div");

    private By bunsSection = By.xpath("//h2[contains(text(), 'Булки')]");
    private By saucesSection = By.xpath("//h2[contains(text(), 'Соусы')]");
    private By fillingsSection = By.xpath("//h2[contains(text(), 'Начинки')]");

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
            // Проверяем наличие класса активности у родительского div
            WebElement bunsElement = driver.findElement(bunsTab);
            String className = bunsElement.getAttribute("class");
            System.out.println("Класс элемента 'Булки': " + className);
            return className.contains("current") || className.contains("active") || className.contains("tab_tab_type_current");
        } catch (Exception e) {
            System.out.println("Ошибка при проверке активности 'Булки': " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка активности раздела 'Соусы'")
    public boolean isSaucesSectionActive() {
        try {
            WebElement saucesElement = driver.findElement(saucesTab);
            String className = saucesElement.getAttribute("class");
            System.out.println("Класс элемента 'Соусы': " + className);
            return className.contains("current") || className.contains("active") || className.contains("tab_tab_type_current");
        } catch (Exception e) {
            System.out.println("Ошибка при проверке активности 'Соусы': " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка активности раздела 'Начинки'")
    public boolean isFillingsSectionActive() {
        try {
            WebElement fillingsElement = driver.findElement(fillingsTab);
            String className = fillingsElement.getAttribute("class");
            System.out.println("Класс элемента 'Начинки': " + className);
            return className.contains("current") || className.contains("active") || className.contains("tab_tab_type_current");
        } catch (Exception e) {
            System.out.println("Ошибка при проверке активности 'Начинки': " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка отображения раздела 'Булки'")
    public boolean isBunsSectionDisplayed() {
        return isElementDisplayed(bunsSection);
    }

    @Step("Проверка отображения раздела 'Соусы'")
    public boolean isSaucesSectionDisplayed() {
        return isElementDisplayed(saucesSection);
    }

    @Step("Проверка отображения раздела 'Начинки'")
    public boolean isFillingsSectionDisplayed() {
        return isElementDisplayed(fillingsSection);
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoad() {
        waitForElementVisible(constructorTitle);
    }

    @Step("Ожидание отображения раздела 'Булки'")
    public void waitForBunsSectionDisplayed() {
        waitForElementVisible(bunsSection);
    }

    @Step("Ожидание отображения раздела 'Соусы'")
    public void waitForSaucesSectionDisplayed() {
        waitForElementVisible(saucesSection);
    }

    @Step("Ожидание отображения раздела 'Начинки'")
    public void waitForFillingsSectionDisplayed() {
        waitForElementVisible(fillingsSection);
    }
}