# exchange_bot
Бот по заданию PanDev для перевода аудио в текст.

## Запуск

- Скачиваем весь проект. Нам нужна ветка 'dev'.
- Запускаем Docker
- В корне проекта есть файл docker-compose.yaml ( в нем запускается 2 контейнера с базой данных и jdk, порты 5432 и 8080 ). 
Пароли, логины и хосты можно поменять в самом файле.
- Запускаем его командой docker compose up
- Документация Api реализована с помощью свагера по адресу http://localhost:8080/swagger-ui/index.html. 
После запуска будет доступна по порту, указанному в файле выше.

##Презентация проекта по основному функционалу



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
