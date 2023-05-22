package com.example.pandev.exchange_bot.util;

import com.example.pandev.exchange_bot.entity.Chat;
import com.example.pandev.exchange_bot.entity.Message;
import com.example.pandev.exchange_bot.repository.ChatRepository;
import com.example.pandev.exchange_bot.repository.MessageRepository;
import org.springframework.stereotype.Component;


/**
 * Класс для сохранения сообщений
 */

@Component
public class SaveMessages {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public SaveMessages(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * Сохранение сообщений
     */
    public void saveMessage(long id, String text) {
        Chat chat;
        chatRepository.save(new Chat(id,ALL_TEXT.STATUS_FREE));
        chat = chatRepository.findById(id).get();
        Message message = new Message();
        message.setText(text);
        message.setChat(chat);
        messageRepository.save(message);
    }

    /**
     * Сохранение сообщений со статусом
     */
    public void saveMessageWithChatStatus(long id, String text, String status) {
        Chat chat;
        chatRepository.save(new Chat(id,status));
        chat = chatRepository.findById(id).get();
        Message message = new Message();
        message.setText(text);
        message.setChat(chat);
        messageRepository.save(message);
    }
}
