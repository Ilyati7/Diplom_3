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

        try {
            driver.get(baseUrl);
            MainPage mainPage = new MainPage(driver);
            mainPage.waitForLoad();

            // Переходим на другую секцию, чтобы потом вернуться к булкам
            mainPage.clickSaucesTab();
            Thread.sleep(1000);

            // Возвращаемся к булкам
            mainPage.clickBunsTab();
            Thread.sleep(1000);

            // Проверяем, что активна секция булок
            assertTrue("Секция 'Булки' должна быть активна",
                    mainPage.isBunsSectionActive());

            System.out.println("✅ Переход к разделу 'Булки' успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при переходе к разделу 'Булки': " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу с соусами в конструкторе бургера")
    public void testNavigateToSaucesSection() {
        System.out.println("Тест: переход к разделу 'Соусы'");

        try {
            driver.get(baseUrl);
            MainPage mainPage = new MainPage(driver);
            mainPage.waitForLoad();

            // Переходим к соусам
            mainPage.clickSaucesTab();
            Thread.sleep(1000);

            // Проверяем, что активна секция соусов
            assertTrue("Секция 'Соусы' должна быть активна",
                    mainPage.isSaucesSectionActive());

            System.out.println("✅ Переход к разделу 'Соусы' успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при переходе к разделу 'Соусы': " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу с начинками в конструкторе бургера")
    public void testNavigateToFillingsSection() {
        System.out.println("Тест: переход к разделу 'Начинки'");

        try {
            driver.get(baseUrl);
            MainPage mainPage = new MainPage(driver);
            mainPage.waitForLoad();

            // Переходим к начинкам
            mainPage.clickFillingsTab();
            Thread.sleep(1000);

            // Проверяем, что активна секция начинок
            assertTrue("Секция 'Начинки' должна быть активна",
                    mainPage.isFillingsSectionActive());

            System.out.println("✅ Переход к разделу 'Начинки' успешен");

        } catch (Exception e) {
            System.out.println("❌ Ошибка при переходе к разделу 'Начинки': " + e.getMessage());
            throw new AssertionError("Тест не прошел: " + e.getMessage());
        }
    }
}