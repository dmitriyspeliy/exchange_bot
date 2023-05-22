package com.example.pandev.exchange_bot.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import pan.dev.mainHandler.MainHandler;

/**
 * наш бот
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ExchangeRatesBotConfig extends SpringWebhookBot {


    String webHookPath;
    String botUserName;
    String botToken;

    @Autowired
    MainHandler mainHandler;

    public ExchangeRatesBotConfig(SetWebhook webhook) {
        super(webhook);
    }



    /**
     * @param update уведомление от пользвателя
     * @return ответ пользователю
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            execute(mainHandler.process(update));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }


  @Override
  public String getBotPath() {
    return webHookPath;
  }

  @Override
  public String getBotUsername() {
    return botUserName;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}