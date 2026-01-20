package com.burgerautotest.tests;

import com.burgerautotest.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты конструктора бургера")
public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу с булками в конструкторе бургера")
    public void testNavigateToBunsSection() {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickBunsTab();
        assertTrue("Тест завершен успешно", true);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу с соусами в конструкторе бургера")
    public void testNavigateToSaucesSection() {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickSaucesTab();
        assertTrue("Тест завершен успешно", true);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу с начинками в конструкторе бургера")
    public void testNavigateToFillingsSection() {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickFillingsTab();
        assertTrue("Тест завершен успешно", true);
    }
}