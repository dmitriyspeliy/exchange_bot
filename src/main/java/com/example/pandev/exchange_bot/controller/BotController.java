package com.example.pandev.exchange_bot.controller;



import com.example.pandev.exchange_bot.configuration.ExchangeRatesBotConfig;
import com.example.pandev.exchange_bot.loger.FormLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


/**
 * Контроллер для бота в телеге
 */
@RestController
@Slf4j
public class BotController {
    private final ExchangeRatesBotConfig exchangeRatesBotConfig;

    public BotController(ExchangeRatesBotConfig exchangeRatesBotConfig) {
        this.exchangeRatesBotConfig = exchangeRatesBotConfig;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public PartialBotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        log.info(FormLogInfo.getInfo("Получили сообщение от бота"));
        return exchangeRatesBotConfig.onWebhookUpdateReceived(update);
    }
}
