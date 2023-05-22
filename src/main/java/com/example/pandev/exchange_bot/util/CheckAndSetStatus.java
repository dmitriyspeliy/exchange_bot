package com.example.pandev.exchange_bot.util;

import com.example.pandev.exchange_bot.entity.Chat;
import com.example.pandev.exchange_bot.repository.ChatRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * Класс для проверки статуса, его установки
 */

@Component
public class CheckAndSetStatus {

    private final ChatRepository chatRepository;

    public CheckAndSetStatus(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /**
     * Проверка статуса
     */
    public boolean checkStatus(Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isEmpty()) return true;
        return chatRepository.findById(id).get().getStatus().equals(ALL_TEXT.STATUS_FREE);
    }

    /**
     * Установка статуса FREE
     */
    public void setStatusFree(Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            Chat newChat = new Chat();
            newChat.setStatus(ALL_TEXT.STATUS_FREE);
            newChat.setChatId(chat.get().getChatId());
            chatRepository.save(newChat);
        } else {
            Chat newChat = new Chat();
            newChat.setStatus(ALL_TEXT.STATUS_FREE);
            chatRepository.save(newChat);
        }
    }


}
