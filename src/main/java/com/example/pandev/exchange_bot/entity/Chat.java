package com.example.pandev.exchange_bot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность для чата
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Chat {

    /**
     * Идентификатор чата в телеге
     */
    @Id
    Long chatId;

    /**
     * статус чата
     */
    @Column(name = "status")
    String status;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @JsonBackReference
    List<Message> messages;

    public Chat(Long chatId,String status) {
        this.chatId = chatId;
        this.status = status;
    }
}
