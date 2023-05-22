package com.example.pandev.exchange_bot.configuration;

import com.example.pandev.exchange_bot.mainHandler.MainHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

/**
 * наш бот
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ExchangeRatesBotConfig extends SpringWebhookBot {


    @Value(value = "${telegram.bot.webHookPath}")
    String webHookPath;
    @Value(value = "${telegram.bot.userName}")
    String botUserName;
    @Value(value = "${telegram.bot.token}")
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