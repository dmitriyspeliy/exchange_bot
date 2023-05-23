# exchange_bot
Бот по заданию PanDev для перевода аудио в текст.

## Запуск

- Скачиваем весь проект. Нам нужна ветка 'dev'.
- Настраиваем переменные среды.
Нам нужно
1. TELEGRAM_URL - получить его можно либо скачать ngrok(запустить у себя на локалке)
https://ngrok.com/docs/getting-started/
либо через docker 
https://ngrok.com/docs/using-ngrok-with/docker/

2.APILAYER_KEY - ключ апи для перевода валюты
 https://dev.vk.com/api/access-token/getting-started
 
3. VK_TOKEN - ключ апи для перевода аудио в текст
https://dev.vk.com/api/access-token/getting-started

- Запускаем Docker
- В корне проекта есть файл docker-compose.yaml ( в нем запускается 2 контейнера с базой данных и jdk, порты 5432 и 8080 ). 
Пароли, логины и хосты можно поменять в самом файле.
- Запускаем его командой docker compose up
После запуска будет доступна по порту, указанному в файле выше.
- Находим бот в телеге @ExchRatesPanDev_bot

## Презентация проекта по основному функционалу



https://github.com/dmitriyspeliy/exchange_bot/assets/102532626/fa5c0bd7-5f91-4d0f-aa18-508953e8bb3a



## Реализованный функционал

- Получение и отправка текстовых сообщений
- Запись всех полученных сообщений (текстовых и голосовых) в базу данных
- Перевод рублей в тенге и обратно. Проект расчитан на расширение списка валют

## Используемые технологии

- Java 11
- Spring 5.3.24
- Spring Boot 2.7.7
- PostgreSQL 15
- Liquibase
- Java-Telegram-Bot-Api
- ApiLayer для валют
- Api.Vk.com для перевода аудио в текст

## Структура данных:

![chat](https://github.com/dmitriyspeliy/exchange_bot/assets/102532626/e0e6eec2-3050-4c28-9d3a-e272a9a83748)


## Автор проекта

- <a  href="https://github.com/dmitriyspeliy">Поспелов Дмитрий</a> 
