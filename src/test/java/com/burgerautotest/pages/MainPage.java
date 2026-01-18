package com.burgerautotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private By loginAccountButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private By personalAccountButton = By.xpath("//a[contains(@href, 'account') or contains(text(), 'Личный Кабинет')]");
    private By constructorTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");

    private By bunsTab = By.xpath("//span[contains(text(), 'Булки')]");
    private By saucesTab = By.xpath("//span[contains(text(), 'Соусы')]");
    private By fillingsTab = By.xpath("//span[contains(text(), 'Начинки')]");

    private By activeTab = By.xpath("//div[contains(@class, 'current') or contains(@class, 'active') or contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginAccountButton() {
        clickElement(loginAccountButton);
    }

    public void clickPersonalAccountButton() {
        clickElement(personalAccountButton);
    }

    public void clickBunsTab() {
        clickElement(bunsTab);
    }

    public void clickSaucesTab() {
        clickElement(saucesTab);
    }

    public void clickFillingsTab() {
        clickElement(fillingsTab);
    }

    public boolean isConstructorDisplayed() {
        return isElementDisplayed(constructorTitle);
    }

    public boolean isBunsSectionActive() {
        try {
            String activeText = driver.findElement(activeTab).getText();
            return activeText.contains("Булки");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaucesSectionActive() {
        try {
            String activeText = driver.findElement(activeTab).getText();
            return activeText.contains("Соусы");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFillingsSectionActive() {
        try {
            String activeText = driver.findElement(activeTab).getText();
            return activeText.contains("Начинки");
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForLoad() {
        // Ждем либо заголовок, либо кнопку входа
        try {
            waitForElementVisible(constructorTitle);
        } catch (Exception e) {
            waitForElementVisible(loginAccountButton);
        }
    }
}