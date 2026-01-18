package com.burgerautotest.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import java.util.List;

public class SimpleNavigationTest extends BaseTest {

    @Test
    public void testWebsiteAvailability() {
        System.out.println("Проверка доступности сайта...");

        // 1. Открываем главную страницу
        driver.get(baseUrl);
        System.out.println("Открыта главная страница: " + driver.getCurrentUrl());
        System.out.println("Заголовок страницы: " + driver.getTitle());

        // Ждем загрузки
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2. Ищем элементы на главной странице
        System.out.println("\nПоиск элементов на главной странице:");
        findElements("//button");
        findElements("//a");
        findElements("//input");
        findElements("//h1");
        findElements("//h2");

        // 3. Проверяем страницу регистрации
        System.out.println("\nПроверка страницы регистрации:");
        driver.get(baseUrl + "/register");
        System.out.println("URL регистрации: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        findElements("//input[@name='name']");
        findElements("//input[@type='email']");
        findElements("//input[@type='password']");
        findElements("//button[contains(text(), 'Зарегистрироваться')]");

        // 4. Проверяем страницу входа
        System.out.println("\nПроверка страницы входа:");
        driver.get(baseUrl + "/login");
        System.out.println("URL входа: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        findElements("//input[@name='name']");
        findElements("//input[@type='password']");
        findElements("//button[contains(text(), 'Войти')]");
    }

    private void findElements(String xpath) {
        try {
            List<org.openqa.selenium.WebElement> elements = driver.findElements(By.xpath(xpath));
            System.out.println("Найдено элементов по '" + xpath + "': " + elements.size());
            if (!elements.isEmpty() && elements.size() < 10) {
                for (int i = 0; i < Math.min(elements.size(), 3); i++) {
                    try {
                        System.out.println("  Элемент " + (i + 1) + ": " +
                                elements.get(i).getTagName() +
                                " text='" + elements.get(i).getText() + "'" +
                                " placeholder='" + elements.get(i).getAttribute("placeholder") + "'");
                    } catch (Exception e) {
                        // Пропускаем ошибки получения атрибутов
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при поиске по '" + xpath + "': " + e.getMessage());
        }
    }
}