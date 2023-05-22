package com.example.pandev.exchange_bot.configuration;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import pan.dev.util.ConfigMenu;


/**
 * Настройка бота
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
public class TelegramBotConfiguration {

    /**
     * токен бота
     */
    @Value("${telegram.bot.token}")
    String token;

    /**
     * ссылка на адрес ( либо ngrok, либо сервер)
     */
    @Value("${telegram.bot.webHookPath}")
    String webHookPath;

    /**
     * название бота в телеге
     */
    @Value("${telegram.bot.userName}")
    String userName;

    final ConfigMenu configMenu;

    public TelegramBotConfiguration(ConfigMenu configMenu) {
        this.configMenu = configMenu;
    }


    /**
     * установка url для хука
     *
     * @return хук
     */
    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(this.getWebHookPath()).build();
    }

    /**
     * установка хука для бота
     *
     * @param setWebhook наш хук выше
     * @return бот с установленным хуком
     * @see ExchangeRatesBotConfig
     */
    @Bean
    public ExchangeRatesBotConfig springWebhookBot(SetWebhook setWebhook) {
        ExchangeRatesBotConfig bot = new ExchangeRatesBotConfig(setWebhook);

        bot.setWebHookPath(this.getWebHookPath());
        bot.setBotUserName(this.getUserName());
        bot.setBotToken(this.getToken());
        configMenu.initMenu(bot);

        return bot;
    }

}
