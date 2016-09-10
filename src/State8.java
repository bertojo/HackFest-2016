import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;

public class State8 {
    
    public static void run(Game game, AvalonBot bot) {         
        for (int i = 0; i < game.pendingMissionPlayers.size(); i++) {
            bot.sendMessage("Type /success or /failure", game.pendingMissionPlayers.get(i).id);
        }
        game.state++;
    }
        
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow test = new KeyboardRow();
//        test.add(new KeyboardButton("Success"));
//        test.add(new KeyboardButton("Failure"));
//        keyboard.add(test);
//        
//        ReplyKeyboardMarkup buttonReply = new ReplyKeyboardMarkup();
//        buttonReply.setSelective(true);
//        buttonReply.setResizeKeyboard(true);
//        buttonReply.setOneTimeKeyboad(true);
//        buttonReply.setKeyboard(keyboard);
//        
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setReplyMarkup(buttonReply);
//        try {
//            bot.sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } 
}
