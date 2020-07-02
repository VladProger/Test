package gall_bot;

import gall_bot.service.GallBotService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new GallBotService());
        } catch (TelegramApiRequestException var3) {
            var3.printStackTrace();
        }
        Game game= new Game();


    }
}

