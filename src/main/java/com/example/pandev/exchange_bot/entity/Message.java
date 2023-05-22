package com.example.pandev.exchange_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Сущность для сообщения
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Message {

    /**
     * Идентификатор cообщения
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long message_id;

    /**
     * Текст сообщения
     */
    @Column(name = "text")
    String text;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chat_id")
    Chat chat;

}

