package com.burgerautotest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Файл application.properties не найден!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url", "https://stellarburgers.nomoreparties.site");
    }

    public static String getBrowserType() {
        return System.getProperty("browser.type", properties.getProperty("browser.type", "chrome"));
    }
}