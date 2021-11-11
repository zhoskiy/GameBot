package com.example.gamebot.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingCommandBot {
    private final String botUsername = "GameHelper_BSUIR_bot";
    private final String botToken = "2113068927:AAGkWpkd2Vyl7S-Ms3lHk1sMFzPnh03nuBY";

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = msg.getFrom().getUserName();
        System.out.println(userName);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(userName);
        sendMessage.setChatId(chatId.toString());

        execute(sendMessage);
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
