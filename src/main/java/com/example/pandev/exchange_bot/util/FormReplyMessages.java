package com.example.pandev.exchange_bot.util;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

/**
 * сервис для формирования сообщений
 */
@Service
@Getter
public class FormReplyMessages {


    /**
     * сформировать ответное сообщение, с указаным текстом и клавиатурой
     */
    public SendMessage replyMessage(String chatId, String textReplyMessage,
                                    ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage(chatId, textReplyMessage);
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    /**
     * сформировать ответное сообщение, с указаным текстом и inline клавиатурой
     */
    public SendMessage replyMessage(String chatId, String textReplyMessage,
                                    InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage(chatId, textReplyMessage);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

}