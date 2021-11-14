package com.example.gamebot.controller;

import com.example.gamebot.controller.command.PopularGamesCommand;
import com.example.gamebot.controller.command.StartCommand;
import com.example.gamebot.service.GameService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME = "GameHelper_BSUIR_bot";
    private final String BOT_TOKEN = "2113068927:AAGkWpkd2Vyl7S-Ms3lHk1sMFzPnh03nuBY";

    // private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public Bot() {
        super();
        registerCommands();
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = msg.getFrom().getUserName();
        System.out.println(msg.getText());
        System.out.println(userName);
        CallbackQuery callbackQuery = new CallbackQuery();
        callbackQuery.setData("/popular");
        switch (msg.getText()) {
            case "pop" ->  update.setCallbackQuery(callbackQuery);
            case "Популярные игры" -> new GameService().findTopGames("").forEach(game -> sendMessage(chatId, game.toString()));
            default -> sendMessage(chatId, String.format("Да, да, я тебя слышу %s, но пока я не знаю, как ответить)", userName));
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    private void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());

        execute(sendMessage);
    }

    private void registerCommands() {
        register(new StartCommand("start", "старт"));
        register(new PopularGamesCommand("popular", "Популярные игры"));
    }
}
