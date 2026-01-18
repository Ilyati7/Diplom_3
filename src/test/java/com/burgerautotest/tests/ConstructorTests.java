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
        System.out.println("Тест: переход к разделу 'Булки'");

        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();

        // Проверяем, что изначально отображается раздел "Булки"
        assertTrue("Изначально должен отображаться раздел 'Булки'",
                mainPage.isBunsSectionDisplayed());

        // Переходим на другую секцию, чтобы потом вернуться к булкам
        mainPage.clickSaucesTab();
        mainPage.waitForSaucesSectionDisplayed();

        // Проверяем, что теперь отображаются соусы
        assertTrue("После клика на 'Соусы' должен отображаться раздел 'Соусы'",
                mainPage.isSaucesSectionDisplayed());

        // Возвращаемся к булкам
        mainPage.clickBunsTab();
        mainPage.waitForBunsSectionDisplayed();

        // Проверяем, что снова отображаются булки
        assertTrue("После клика на 'Булки' должен отображаться раздел 'Булки'",
                mainPage.isBunsSectionDisplayed());

        System.out.println("✅ Переход к разделу 'Булки' успешен");
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу с соусами в конструкторе бургера")
    public void testNavigateToSaucesSection() {
        System.out.println("Тест: переход к разделу 'Соусы'");

        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();

        // Переходим к соусам
        mainPage.clickSaucesTab();
        mainPage.waitForSaucesSectionDisplayed();

        // Проверяем, что отображается раздел соусов
        assertTrue("После клика на 'Соусы' должен отображаться раздел 'Соусы'",
                mainPage.isSaucesSectionDisplayed());

        System.out.println("✅ Переход к разделу 'Соусы' успешен");
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу с начинками в конструкторе бургера")
    public void testNavigateToFillingsSection() {
        System.out.println("Тест: переход к разделу 'Начинки'");

        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();

        // Переходим к начинкам
        mainPage.clickFillingsTab();
        mainPage.waitForFillingsSectionDisplayed();

        // Проверяем, что отображается раздел начинок
        assertTrue("После клика на 'Начинки' должен отображаться раздел 'Начинки'",
                mainPage.isFillingsSectionDisplayed());

        System.out.println("✅ Переход к разделу 'Начинки' успешен");
    }
}