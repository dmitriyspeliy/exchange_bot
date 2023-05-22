package com.example.pandev.exchange_bot.mainHandler;

import com.example.pandev.exchange_bot.loger.FormLogInfo;
import com.example.pandev.exchange_bot.util.CheckAndSetStatus;
import com.example.pandev.exchange_bot.util.SaveMessages;
import com.example.pandev.exchange_bot.util.TransferAudioToText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


/**
 * Класс обработки аудио сообщений
 */

@Component
@Slf4j
public class VoiceHandler {

    private final CheckAndSetStatus checkAndSetStatus;
    private final SaveMessages saveMessage;
    private final TransferAudioToText transferAudioToText;

    public VoiceHandler(CheckAndSetStatus checkAndSetStatus, SaveMessages saveMessage, TransferAudioToText transferAudioToText) {
        this.checkAndSetStatus = checkAndSetStatus;
        this.saveMessage = saveMessage;
        this.transferAudioToText = transferAudioToText;
    }

    public SendMessage getVoise(GetBaseInfoFromUpdate baseInfo) {
        if (!checkAndSetStatus.checkStatus(baseInfo.getChatIdL())) {
            checkAndSetStatus.setStatusFree(baseInfo.getChatIdL());
        }
        log.info(FormLogInfo.getInfo("Обрабатваем звук в сообщение"));
        String text = transferAudioToText.getTextFromAudio(baseInfo.getFileId());
        saveMessage.saveMessage(baseInfo.getChatIdL(), text);
        return new SendMessage(baseInfo.getChatId()
                , "Сообещние сохранено, текст сообщения \n : " + text);
    }

}
