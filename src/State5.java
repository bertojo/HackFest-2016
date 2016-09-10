import java.awt.List;
import java.util.ArrayList;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardHide;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class State5 {
    public static void kingChoosePlayers(AvalonBot bot, Game game) {
        Player king = game.king;
        int numOfQuesters = game.map.missionPlayerCount[game.currentQuest];
        int numChosen = 0;
        bot.sendMessage("The king shall now choose who will go on the quest.", game.gameId);
        ReplyKeyboardMarkup specialKeyboard = getQuestChoosingKeyboard(bot, game);
        SendMessage keyboardMessage = new SendMessage();
        String gameID = new String("" + game.gameId + "");
        keyboardMessage.setChatId(gameID);
        keyboardMessage.enableMarkdown(true);
        keyboardMessage.setReplyMarkup(specialKeyboard);
<<<<<<< HEAD
        bot.sendMessage("Please choose " + numOfQuesters + " players.", king.id);
        
        try{
            bot.sendMessage(keyboardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
        if (numOfQuesters) {
            
        } 
=======
        //bot.sendMessage("Please choose " + game.map.missionPlayerCount[game.questNumber] + " players.", king.id);
        //bot.sendMessage(keyboardMessage);
>>>>>>> 0918ceb31051a5ff24b6c0835d183af070480c62
    }

    public static ReplyKeyboardMarkup getQuestChoosingKeyboard(AvalonBot bot, Game game) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(false);

        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        for (int i = 0; i < game.players.size(); i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(game.players.get(i).name));
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

}
