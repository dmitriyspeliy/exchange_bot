package com.example.pandev.exchange_bot.mainHandler;

import com.example.pandev.exchange_bot.loger.FormLogInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class GetBaseInfoFromUpdate {

    String chatId;
    Long chatIdL;
    Message message;
    String textMessage;
    boolean isCallbackQuery;
    boolean isVoice;
    boolean isText;
    CallbackQuery callbackQuery;
    String fileId;


    public GetBaseInfoFromUpdate(Update update) {
        init(update);
    }

    public void init(Update update) {
        if (update.getCallbackQuery() != null) {
            log.info(FormLogInfo.getInfo("Соощение от инлайн клавиатуры"));
            callbackQuery = update.getCallbackQuery();
            textMessage = callbackQuery.getData();
            chatIdL = callbackQuery.getMessage().getChatId();
            chatId = chatIdL.toString();
            isCallbackQuery = true;
        } else if (update.getMessage().hasVoice()) {
            log.info(FormLogInfo.getInfo("Аудиосообщение"));
            fileId = update.getMessage().getVoice().getFileId();
            isVoice = true;
            message = update.getMessage();
            chatIdL = message.getChatId();
            chatId = chatIdL.toString();
        } else if (update.getMessage().hasText()) {
            log.info(FormLogInfo.getInfo("Просто текст"));
            isText = true;
            message = update.getMessage();
            textMessage = message.getText();
            chatIdL = message.getChatId();
            chatId = chatIdL.toString();
        }
    }
}
