package com.example.pandev.exchange_bot.util.parseGSON;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс для парсинга техта аудиозаписи и статуса операции из JSON
 */

@Getter
@Setter
public class Text {
    private String text;
    private String status;
}
