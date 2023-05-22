package com.example.pandev.exchange_bot.util;

import com.example.pandev.exchange_bot.configuration.ExchangeRatesBotConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;

/**
 * Класс, где создается меню комнад в боте
 */
@Configuration
@Slf4j
@Data
public class ConfigMenu {
    public void initMenu(ExchangeRatesBotConfig bot){
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/translate", "Перевести одну валюту в другую"));
        listofCommands.add(new BotCommand("/start", "Начать диалог с ботом"));
        try {
            bot.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }
}
