package com.example.pandev.exchange_bot.repository;

import com.example.pandev.exchange_bot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий для сообещения
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
