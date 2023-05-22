package com.example.pandev.exchange_bot.mainHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Component
public class MainHandlerImpl implements MainHandler {

    private final ExchangeHandler exchangeHandler;
    private final TextHandler textHandler;
    private final VoiceHandler voiceHandler;

    public MainHandlerImpl(ExchangeHandler exchangeHandler, TextHandler textHandler, VoiceHandler voiceHandler) {
        this.exchangeHandler = exchangeHandler;
        this.textHandler = textHandler;
        this.voiceHandler = voiceHandler;
    }

    /**
     * Обрабатываем Update делим его по типу сообщения
     */
    @Override
    public SendMessage process(Update update) {

        GetBaseInfoFromUpdate baseInfo = new GetBaseInfoFromUpdate(update);

        return processingIncomingMessages(baseInfo);
    }

    /**
     * Обработка входящих сообщений из update
     */
    private SendMessage processingIncomingMessages(GetBaseInfoFromUpdate baseInfo) {

        //обработка голосовых сообщений
        if (baseInfo.isVoice()) {
           return voiceHandler.getVoise(baseInfo);
        }
        //обработка инлайн сообщений
        if (baseInfo.isCallbackQuery()) {
            return exchangeHandler.getCallbaskQuery(baseInfo);
        }
        //обработка текста
        return textHandler.getTextMessage(baseInfo);

    }

}



