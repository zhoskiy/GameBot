package com.example.gamebot.command;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;

abstract class ServiceBotCommand extends BotCommand {
    private SendMessage message = new SendMessage();
    public ServiceBotCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @SneakyThrows
    void sendMessage(AbsSender absSender, Long chatId, String commandName, String userName, String text){
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);

        absSender.execute(message);
    }

    void sendMessage(AbsSender absSender, Long chatId, String commandName, String userName, String text, ReplyKeyboardMarkup replyKeyboardMarkup){
        message.setReplyMarkup(replyKeyboardMarkup);
        sendMessage(absSender, chatId, commandName, userName, text);
    }
}
