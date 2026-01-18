package com.burgerautotest.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        String browserType = ConfigReader.getBrowserType();
        System.out.println("Запуск браузера: " + browserType);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Базовые опции для стабильности
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");

        // Для Яндекс.Браузера на Windows
        if ("yandex".equalsIgnoreCase(browserType)) {
            // Путь к Яндекс.Браузеру (измените под свою систему)
            // options.setBinary("C:\\Users\\ВашПользователь\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            System.out.println("Запуск Яндекс.Браузера");
        }

        return new ChromeDriver(options);
    }
}