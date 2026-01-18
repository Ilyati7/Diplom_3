package com.burgerautotest.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestDataGenerator {
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomName() {
        return faker.name().firstName();
    }

    public static String generateValidPassword() {
        return faker.internet().password(8, 12, true, true);
    }

    public static String generateShortPassword() {
        return "123"; // Пароль меньше 6 символов
    }
}