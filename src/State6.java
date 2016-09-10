import java.util.List;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class State6 {
    public static void run(Game game) {
        //Approve/Reject for everyone 
        
        //need to get everyone to approve and reject
        //hence the special keyboard is for everyone

        KeyboardButton A = new KeyboardButton("Approve");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard((List<KeyboardRow>) A);
        KeyboardButton B = new KeyboardButton("Reject");
        replyKeyboardMarkup.setKeyboard((List<KeyboardRow>) B);
    }
}
