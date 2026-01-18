🍔 Burger UI Autotests
Проект автоматизированного тестирования UI для Stellar Burgers с использованием Selenium WebDriver, JUnit 4 и Allure Reports.

📋 Оглавление
Технологии

Требования

Настройка проекта

Запуск тестов

Allure отчет

Структура проекта

Тестовые сценарии

Интеграция с CI/CD

🛠 Технологии
Java 11 - основной язык программирования

Selenium WebDriver 4.20.0 - автоматизация браузера

JUnit 4.13.2 - фреймворк для тестирования

Allure 2.24.0 - генерация отчетов

Maven - управление зависимостями и сборка

Lombok - сокращение boilerplate кода

RestAssured - тестирование API

JavaFaker - генерация тестовых данных

📋 Требования
Системные требования:
Java JDK 11 или выше

Maven 3.6+

Браузер Chrome или Яндекс.Браузер

Git

Установка зависимостей:
bash
# Клонирование репозитория
git clone <repository-url>
cd burger-ui-autotests

# Установка зависимостей
mvn clean install
⚙️ Настройка проекта
Конфигурация браузера
Настройки браузера задаются в файле src/test/resources/application.properties:

properties
# Базовый URL тестируемого приложения
base.url=https://stellarburgers.education-services.ru

# Тип браузера (chrome или yandex)
browser.type=chrome
# browser.type=yandex
Настройка для Яндекс.Браузера
Для использования Яндекс.Браузера необходимо:

Установить Яндекс.Браузер

В файле WebDriverFactory.java указать путь к исполняемому файлу

🚀 Запуск тестов
Запуск всех тестов:
bash
mvn clean test
Запуск конкретного класса тестов:
bash
# Тесты регистрации
mvn test -Dtest=RegistrationTests

# Тесты авторизации
mvn test -Dtest=LoginTests

# Тесты конструктора
mvn test -Dtest=ConstructorTests
Запуск конкретного теста:
bash
mvn test -Dtest=LoginTests#testLoginViaMainPageButton
Запуск с разными браузерами:
bash
# Chrome (по умолчанию)
mvn test

# Яндекс.Браузер
mvn test -Dbrowser.type=yandex
📊 Allure отчет
Генерация и просмотр отчета:
bash
# Вариант 1: Автоматический запуск и открытие в браузере
mvn clean test allure:serve

# Вариант 2: Только генерация отчета
mvn clean test
mvn allure:report
# Отчет будет доступен в: target/site/allure-maven-plugin/index.html
Скриншоты отчета:
https://docs/images/allure-overview.png
https://docs/images/allure-suites.png
https://docs/images/allure-graphs.png

Структура отчета:
Overview - общая статистика тестов

Categories - группировка по типам ошибок

Suites - список тестовых наборов

Graphs - графики успешности тестов

Timeline - временная шкала выполнения

Behaviors - группировка по функциональности

📁 Структура проекта
text
src/test/java/com/burgerautotest/
├── models/                    # Модели данных

│   ├── AuthData.java         # Данные для авторизации

│   └── UserData.java         # Данные пользователя

├── pages/                    # Page Object классы

│   ├── BasePage.java         # Базовый класс страницы

│   ├── LoginPage.java        # Страница входа

│   ├── RegistrationPage.java # Страница регистрации

│   ├── MainPage.java         # Главная страница

│   └── ForgotPasswordPage.java # Страница восстановления пароля

├── tests/                    # Тестовые классы

│   ├── BaseTest.java         # Базовый тестовый класс

│   ├── LoginTests.java       # Тесты авторизации

│   ├── RegistrationTests.java # Тесты регистрации

│   └── ConstructorTests.java # Тесты конструктора

└── utils/                    # Вспомогательные классы

    ├── ApiClient.java        # Клиент для API запросов
    
    ├── ConfigReader.java     # Чтение конфигурации
    
    ├── TestDataGenerator.java # Генерация тестовых данных
    
    └── WebDriverFactory.java # Фабрика WebDriver



docs/

├── images/                   # Скриншоты отчетов

│   ├── allure-overview.png

│   ├── allure-suites.png

│   └── allure-graphs.png

└── allure-report/            # Полный HTML отчет



target/
├── allure-results/           # Сырые данные Allure
└── surefire-reports/         # Отчеты Surefire
