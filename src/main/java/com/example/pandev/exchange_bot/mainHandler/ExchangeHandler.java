package com.example.pandev.exchange_bot.mainHandler;

import com.example.pandev.exchange_bot.loger.FormLogInfo;
import com.example.pandev.exchange_bot.util.ALL_TEXT;
import com.example.pandev.exchange_bot.util.SaveMessages;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Класс обработки соощений по теме перевода валют
 */

@Component
@Slf4j
public class ExchangeHandler {

    @Value(value = "${apilayer.key}")
    private String key;

    @Value(value = "${apilayer.url}")
    private String url;
    private final SaveMessages saveMessages;


    public ExchangeHandler(SaveMessages saveMessages) {
        this.saveMessages = saveMessages;
    }

    /**
     * Ответ на сообщение о валюте
     *
     * @return сообщение о вводе количества нужной нам валюте
     */
    public SendMessage getCallbaskQuery(GetBaseInfoFromUpdate baseInfo) {
        SendMessage sendMessage = null;
        if (baseInfo.getTextMessage().equalsIgnoreCase(ALL_TEXT.TENGE)) {
            saveMessages.saveMessageWithChatStatus(baseInfo.getChatIdL(), ALL_TEXT.TENGE, ALL_TEXT.STATUS_CONVERT_TENGE);
            sendMessage = new SendMessage(baseInfo.getChatId(), "Введите сумму в рублях:");
        } else if (baseInfo.getTextMessage().equalsIgnoreCase(ALL_TEXT.RUBLE)) {
            saveMessages.saveMessageWithChatStatus(baseInfo.getChatIdL(), ALL_TEXT.RUBLE, ALL_TEXT.STATUS_CONVERT_RUB);
            sendMessage = new SendMessage(baseInfo.getChatId(), "Введите сумму в тенге:");
        }
        return sendMessage;
    }



    /**
     * Запрос на стоимость валюты. Тут сколько получим тенге имеея amount рублей
     *
     * @return стоимость валюты
     */
    public String getConvertRub(String amount) {
        log.info(FormLogInfo.getInfo("Конвертируем валюты"));
        String result;
        try {
            JSONObject url = Unirest.get(this.url)
                    .header("apiKey", this.key)
                    .queryString("to", ALL_TEXT.KZT)
                    .queryString("from", ALL_TEXT.RUB)
                    .queryString("amount", amount)
                    .asJson().getBody().getObject();

            BigDecimal bigDecimal = url.getBigDecimal("result");
            bigDecimal = bigDecimal.setScale(0, RoundingMode.DOWN);
            result = bigDecimal.toString();

        } catch (UnirestException e) {
            throw new RuntimeException(e);

        }
        return result;
    }

    /**
     * Запрос на стоимость валюты. Тут сколько получим рублей имеея amount тенге
     *
     * @return стоимость валюты
     */
    public String getConvertKZT(String amount) {
        log.info(FormLogInfo.getInfo("Конвертируем валюты"));
        String result;
        try {
            JSONObject url = Unirest.get(this.url)
                    .header("apiKey", this.key)
                    .queryString("to", ALL_TEXT.RUB)
                    .queryString("from", ALL_TEXT.KZT)
                    .queryString("amount", amount)
                    .asJson().getBody().getObject();


            BigDecimal bigDecimal = url.getBigDecimal("result");
            bigDecimal = bigDecimal.setScale(0, RoundingMode.DOWN);
            result = bigDecimal.toString();

        } catch (UnirestException e) {
            throw new RuntimeException(e);

        }
        return result;
    }


}
