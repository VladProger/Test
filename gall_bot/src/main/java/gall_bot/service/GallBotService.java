package gall_bot.service;

import gall_bot.dto.UserDto;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class GallBotService extends TelegramLongPollingBot {
    private static final String TOKEN = "1233467258:AAHCOQ8GqD6lJy3NVP0Ae487DsCuGxRh0cE";
    private static final String BOT_NAME = "gallows_new_game_bot";
    private final MessageService messageService = new MessageService();

    public void onUpdateReceived(Update update) { // метод отримує повідомлення
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChat().getId();
        messageService.messageListener(new UserDto(text, chatId), chatId);
    }

    public void sendMessage(String text, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        buttons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void buttons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup= new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Play now"));
        keyboardFirstRow.add(new KeyboardButton("Check my score"));

        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

    }

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return TOKEN;
    }
}
