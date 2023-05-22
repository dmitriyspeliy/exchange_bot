package com.example.pandev.exchange_bot.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, где создается кнопки в боте
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@Slf4j
public class ConfigKeyBoard {


    /**
     * Метод формирует клавиатуры из переданного текста
     *
     * @param textButtons текст кнопок, список может быть любой длинны
     * @return клавиатуру с текстом кнопок из textButtons
     */
    public ReplyKeyboardMarkup formReplyKeyboardInOneRow(String... textButtons) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> KEYBOARD_BUTTONS = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (String textButton : textButtons) {
            row.add(new KeyboardButton(textButton));
        }
        KEYBOARD_BUTTONS.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(KEYBOARD_BUTTONS);
        return keyboardMarkup;
    }

    /**
     * Метод формирует инлайн клавиатуру из переданного текста
     *
     * @param textButtons текст кнопок, список может быть любой длинны
     * @return инлайн клавиатуру
     */
    public InlineKeyboardMarkup formReplyKeyboardInOneRowInline(String... textButtons) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> listRow = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (String textButton : textButtons) {
            InlineKeyboardButton button = new InlineKeyboardButton(textButton);
            button.setCallbackData(textButton);
            row.add(button);
        }
        listRow.add(row);
        inlineKeyboardMarkup.setKeyboard(listRow);

        return inlineKeyboardMarkup;
    }
}