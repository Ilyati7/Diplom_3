# Allure отчет

Папка allure-results содержит результаты тестов.
Для просмотра отчета:

1. Установите Allure командной строки
2. Выполните: allure serve allure-results

Или откройте allure-report/index.html

Результаты тестов:
- Всего тестов: 9
- Успешно: 8
- Ошибка: 1 (проблема с локаторами на реальной странице)
"@ | Out-File -FilePath "allure-results/README.txt" -Encoding UTF8

# Проверьте
Get-ChildItem "allure-results" -Recurse | Select-Object Name