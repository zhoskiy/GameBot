package com.example.gamebot.controller.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Collections;
import java.util.List;

public class StartCommand extends ServiceBotCommand {
    private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        customizeButtons();

        String username = getUsername(user);

        StringBuilder text = new StringBuilder();
        text.append(String.format("Привет %s !", username))
                .append(" ")
                .append("""
                        Я бот, который поможет подобрать тебе игру. Давай начнем!
                                        
                        Если тебе нужна помощь, то напиши команду /help
                        """);

        sendMessage(absSender, chat.getId(), this.getCommandIdentifier(), username, String.valueOf(text), replyKeyboardMarkup);

    }

    private void customizeButtons() {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Популярные_игры");
        keyboardRow.add("Помощь");


        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
    }

    private String getUsername(User user) {
        return user.getUserName() != null ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
    }
}
