package gall_bot.service;
import gall_bot.Game;
import gall_bot.dto.UserDto;
import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.games.Game;
//import gall_bot.Game;

public class MessageService {
    private final static String START_MESSAGE = "/start";
    private final static String HELP_MESSAGE= "/help";
private static final String GREETING_MESSAGE= "HI BRO!!!!\n In this game you should to guess a word.\n" +
        "You will have few tries to check if the word consist a given letter.\n" +
        "Also you are able to guess a word sooner, if you already know an answer\n" +
        "In this way just enter '*'\n" +
        "But be careful, you only have 1 try to guess a whole word\n" +
        "Good luck!";
    public void messageListener(UserDto userDto, Long chatId) {

        GallBotService gallBotService = new GallBotService();
        Game game1 =new Game();
        Update update=new Update();

        if (userDto.getText().equals(START_MESSAGE)|| userDto.getText().equals(HELP_MESSAGE)) {
            gallBotService.sendMessage(GREETING_MESSAGE, userDto.getChatId());
        }else if(userDto.getText().equals("Play now")){
          //  game1.wordGeneration(chatId);
            game1.completeGame(chatId, update);


            //тут починається гра
            System.out.println("Game is started");
        }else if(userDto.getText().equals("Check my score")){
            System.out.println("Score is checked");
            //тут виводимо бали
        }

    }

}
