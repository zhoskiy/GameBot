package com.example.gamebot.controller.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class PopularGamesCommand extends ServiceBotCommand {
    private String text = "Здесь будет показано топ 10 самых популярных игр в Steam на сегодняшний день";

    public PopularGamesCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendMessage(absSender, user.getId(), this.getCommandIdentifier(), user.getUserName(), text);
    }
}
