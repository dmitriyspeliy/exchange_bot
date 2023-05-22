package com.example.pandev.exchange_bot.util;

import com.example.pandev.exchange_bot.loger.FormLogInfo;
import com.example.pandev.exchange_bot.util.parseGSON.FileInfo;
import com.example.pandev.exchange_bot.util.parseGSON.TaskId;
import com.example.pandev.exchange_bot.util.parseGSON.Text;
import com.example.pandev.exchange_bot.util.parseGSON.UploadUrl;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Класс для преобразования аудио в текст
 */
@Component
@Slf4j
public class TransferAudioToText {

    @Value(value = "${vk.version}")
    private String version;

    @Value(value = "${vk.token}")
    private String token;

    @Value(value = "${vk.url.api}")
    private String url_api;

    @Value(value = "${telegram.bot.token}")
    private String bot_token;

    //Текст аудиозаписи
    private String text;

    private final Gson GSON = new Gson();


    /**
     * Метод взода в класс
     *
     * @param id аудиозапись
     * @return текст аудиозаписи
     */
    public String getTextFromAudio(String id) {
        return getText(id);
    }


    /**
     * Чтобы получить текстовую расшифровку аудиозаписи, нужно выполнить следующие шаги:
     * 1.Получить адрес сервера для загрузки аудиозаписи.
     * 2.Отправь аудиозапись по адресу сервера загрузки.
     * 3.Запустить распознавание аудиозаписи.
     * 4.Получить результат распознавания аудиозаписи.
     *
     * @return текст аудиозапси
     */
    private String getText(String id) {
        try {

            //Скачиваем файл с сервера телеграмм

            File file = getFile(id);

            //Получить адрес сервера для загрузки аудиозаписи

            JSONObject url = Unirest.get(this.url_api + "asr.getUploadUrl")
                    .queryString("access_token", this.token).queryString("v", this.version).asJson().getBody().getObject();

            //Парсим

            UploadUrl objUploadUrl = GSON.fromJson(url.get(ALL_TEXT.RESPONSE).toString(), UploadUrl.class);
            String uploadUrl = objUploadUrl.getUpload_url();

            //Отправка файла на сервер

            JSONObject responseFroomServer =
                    Unirest.post(uploadUrl)
                            .field("file", file)
                            .asJson().getBody().getObject();


            //Запустить распознавание аудиозаписи

            JSONObject taskIdJson = Unirest.get(this.url_api + "asr.process")
                    .queryString("access_token", this.token)
                    .queryString("audio", responseFroomServer)
                    .queryString("model", ALL_TEXT.MODEL).queryString("v", this.version)
                    .asJson().getBody().getObject();

            //Парсим

            TaskId objTaskId = GSON.fromJson(taskIdJson.get(ALL_TEXT.RESPONSE).toString(), TaskId.class);
            String taskId = objTaskId.getTask_id();

            //Получаем результат распознавания аудиозаписи

            String status = null;
            JSONObject textJS;
            while (!Objects.equals(status, "finished")) {
                try {
                    textJS = Unirest.get(this.url_api + "asr.checkStatus")
                            .queryString("access_token", this.token)
                            .queryString("task_id", taskId).queryString("v", this.version).asJson().getBody().getObject();
                } catch (UnirestException e) {
                    throw new RuntimeException(e);
                }
                Text objText = GSON.fromJson(textJS.get(ALL_TEXT.RESPONSE).toString(), Text.class);
                status = objText.getStatus();
                text = objText.getText();
                Thread.sleep(500);
            }

        } catch (UnirestException e) {
            log.info(FormLogInfo.getCatch());
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    /**
     * Вспомонательный метод для скачивания файла
     *
     * @param id айди файла на сервере
     * @return файл с сервера
     */
    private File getFile(String id) throws UnirestException, IOException {
        JSONObject fileInfoJS = Unirest.get("https://api.telegram.org/bot" + this.bot_token + "/getFile")
                .queryString("file_id", id)
                .asJson().getBody().getObject();

        FileInfo fileInfo = this.GSON.fromJson(fileInfoJS.get("result").toString(), FileInfo.class);
        String filePath = fileInfo.getFile_path();

        Path path = Paths.get("file.oga");
        File file = new File(path.toUri());

        URL url = new URL("https://api.telegram.org/file/bot" + this.bot_token + "/" + filePath);

        download(url, file);

        return file;
    }

    /**
     * Вспомонательный метод для скачивания файла
     */
    private static void download(URL url, File file) throws IOException {
        try {
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        }catch (ConnectException e){
            log.error("Слишком долгий коннект с сервером. Перезапустите сервер");
        }

    }


}

