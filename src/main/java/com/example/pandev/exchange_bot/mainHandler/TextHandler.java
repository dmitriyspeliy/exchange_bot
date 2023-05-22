package com.example.pandev.exchange_bot.mainHandler;

import com.example.pandev.exchange_bot.entity.Chat;
import com.example.pandev.exchange_bot.repository.ChatRepository;
import com.example.pandev.exchange_bot.util.CheckAndSetStatus;
import com.example.pandev.exchange_bot.util.ConfigKeyBoard;
import com.example.pandev.exchange_bot.util.FormReplyMessages;
import com.example.pandev.exchange_bot.util.SaveMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;


@Component
public class TextHandler {

    private final CheckAndSetStatus checkAndSetStatus;
    private final FormReplyMessages formReplyMessages;
    private final ConfigKeyBoard configKeyBoard;
    private final ChatRepository chatRepository;
    private final ExchangeHandler exchangeHandler;
    private final SaveMessages saveMessages;


    public TextHandler(CheckAndSetStatus checkAndSetStatus, FormReplyMessages formReplyMessages, ConfigKeyBoard configKeyBoard, ChatRepository chatRepository, ExchangeHandler exchangeHandler, SaveMessages saveMessages) {
        this.checkAndSetStatus = checkAndSetStatus;
        this.formReplyMessages = formReplyMessages;
        this.configKeyBoard = configKeyBoard;
        this.chatRepository = chatRepository;
        this.exchangeHandler = exchangeHandler;
        this.saveMessages = saveMessages;
    }

    public SendMessage getTextMessage(GetBaseInfoFromUpdate baseInfo) {

        String text = baseInfo.getTextMessage();
        Long id = baseInfo.getChatIdL();

        if (text.equals("/start")) {
            if (!checkAndSetStatus.checkStatus(id)) {
                checkAndSetStatus.setStatusFree(id);
            }

            return new SendMessage(baseInfo.getChatId()
                    , "Всем привет, этот бот может перести одну валюту в другую(пока что это тенге и рубль).\n"
                    + "Чтобы начать перевод, воспользуйтесь кнопками меню.\n" +
                    "Таже бот может переводить аудиозапись в текст и сохранять ее");

        }

        if (text.equals("/translate")) {
            saveMessages.saveMessageWithChatStatus(baseInfo.getChatIdL(), baseInfo.getTextMessage(), "convert");
            return formReplyMessages.replyMessage(baseInfo.getChatId(), "Какую валюту хотите перевести?",
                    configKeyBoard.formReplyKeyboardInOneRowInline("Тенге", "Рубль"));
        }

        if (!checkAndSetStatus.checkStatus(id)) {
            SendMessage sendMessage = null;
            Optional<Chat> chat = chatRepository.findById(id);
            String status = chat.get().getStatus();

            String amount = baseInfo.getTextMessage();
            if (StringUtils.isNumeric(amount)){
                switch (status) {
                    case "convertTenge":
                        sendMessage = new SendMessage(baseInfo.getChatId(), exchangeHandler.getConvertRub(amount));
                        break;
                    case "convertRub":
                        sendMessage = new SendMessage(baseInfo.getChatId(), exchangeHandler.getConvertKZT(amount));
                        break;
                }
            }else{
                sendMessage = new SendMessage(baseInfo.getChatId(), "Количество денег только цифры");
            }
            return sendMessage;
        }

        return new SendMessage(baseInfo.getChatId(), "Лучше запишите аудиосообщение");
    }
}
