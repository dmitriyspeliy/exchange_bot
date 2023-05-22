package com.example.pandev.exchange_bot.repository;

import com.example.pandev.exchange_bot.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для чата
 */

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {


}
