package com.burgerautotest.tests;

import com.burgerautotest.utils.WebDriverFactory;
import com.burgerautotest.utils.ConfigReader;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @Before
    public void setUp() {
        System.out.println("Запуск теста...");
        driver = WebDriverFactory.createDriver();
        baseUrl = ConfigReader.getBaseUrl();
        System.out.println("Base URL: " + baseUrl);

        // Увеличиваем таймауты для стабильности
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("Завершение теста...");
            driver.quit();
        }
    }
}