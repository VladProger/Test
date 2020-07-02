package gall_bot;

import gall_bot.dto.WordDto;
import gall_bot.service.GallBotService;
import gall_bot.service.MessageService;
import gall_bot.dto.UserDto;
import org.telegram.telegrambots.meta.api.objects.Update;
import gall_bot.repository.WordRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {

    GallBotService gallBotService=new GallBotService();

    public static Scanner scan = new Scanner(System.in);
    StringBuilder emptyLine = new StringBuilder("");
    String word = new String();
    public void wordGeneration(Long chatId){
        String[]words={"elephant", "bat", "bison", "chimpanzee", "donkey", "lion", "woolf"};
        word = (words[new Random().nextInt(words.length)]);
        gallBotService.sendMessage(word, chatId);

    }


    public void start(){

        System.out.println("In this game you should to guess a word.\n" +
                "You will have few tries to check if the word consist a given letter.\n" +
                "Also you are able to guess a word sooner, if you already know an answer\n" +
                "In this way just enter '*'\n"+
                "But be careful, you only have 1 try to guess a whole word\n" +
                "Good luck!" );
    }
    StringBuilder amountOfLetters() {
        for (int i = 0; i < word.length(); i++) {
            emptyLine.append("_");
        }
        return emptyLine;
    }

   public void guessALetter(Long chatId, Update update) {



       gallBotService.sendMessage("Please, guess a letter", chatId);
        //System.out.println("Please, guess a letter");
        try {
            int correct = 0;
            //String input = scan.nextLine();
            String input=update.getMessage().getText();
            char letter = input.charAt(0);
            if(letter=='*') geussWholeWord(chatId, update);
            if(Character.isLetter(letter)) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        emptyLine.setCharAt(i, letter);
                        correct++;
                    }
                }
                if (correct < 1) {
                    gallBotService.sendMessage("The word doesn't consist letter ", chatId);
                    //System.out.println("The word doesn't consist letter " + letter);
                }
                System.out.println(emptyLine);
            }else {
                gallBotService.sendMessage("Try again! ", chatId);
               // System.out.println("Try again!");
                guessALetter(chatId, update);
            }

        } catch (IndexOutOfBoundsException e) {
            gallBotService.sendMessage("Try again! ", chatId);
           // System.out.println("Try again!");
            guessALetter(chatId, update);
        }
    }

    public void geussWholeWord(Long chatId, Update update) {
       // System.out.println("Please, guess a word");
        gallBotService.sendMessage("Please, guess a word ", chatId);
        //String guessWord = scan.nextLine();
        String guessWord=update.getMessage().getText();
        if (word.equals(guessWord)) {
            gallBotService.sendMessage("Congratulation! You win! ", chatId);
           // System.out.println("Congratulation! You win!");
        } else //System.out.println("Game over");
        gallBotService.sendMessage("Game over", chatId);
        System.exit(1);
    }

//    void choose() {
//        int amountOfTries = 4;
//        for (int i = amountOfTries; i >= 0;  i--) {
//            if (i ==0 ) geussWholeWord();
//            if (i == 1)
//                System.out.println("You have " + amountOfTries-- + " try\nPlease, choose an action:");
//            else {System.out.println("You have " + amountOfTries-- + " tries\nPlease, choose an action:");
//            }
//            System.out.println("Enter 1 - to quess a letter\n      2 - to guess a whole word");
//
//           // choice();
//           // useTries();
//        }
//    }

    public void useTries(Long chatId, Update update){

        int amountOfTries = 4;
        for (int i = amountOfTries; i >= 0;  i--) {
            String lastTry = "You have " + amountOfTries-- + " try";
            String tries ="You have " + amountOfTries-- + " tries";
            if (i ==0 ) geussWholeWord(chatId, update);
            if (i == 1)

           // String lastTry = "You have " + amountOfTries-- + " try";
            gallBotService.sendMessage(lastTry, chatId);
              //  System.out.println("You have " + amountOfTries-- + " try");
            else {
                //System.out.println("You have " + amountOfTries-- + " tries");
                gallBotService.sendMessage(tries, chatId);
            }
           // System.out.println("Enter 1 - to quess a letter\n      2 - to guess a whole word");

            guessALetter(chatId,update);
        }
        }

        public void completeGame(Long chatId, Update update){
        wordGeneration(chatId);
        amountOfLetters();
        useTries(chatId, update);

        }



//void choice(){
//    try {
//        Scanner scan = new Scanner(System.in);
//        int c = scan.nextInt();
//        switch (c) {
//            case (1):
//                guess();
//                break;
//            case (2):
//                geussWholeWord();
//                break;
//            default:
//                System.out.println("Please Enter '1' or '2'!");
//                 choice();
//                break;
//        }
//    } catch (InputMismatchException e) {
//        System.out.println("Please Enter '1' or '2'!");
//        choice();
//    }
//}

    }
