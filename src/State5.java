import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class State5 {
    public static void run(Game game) {
        Player king = game.king;
        sendMessage("The king shall now choose who will go on the quest.", king.chatId);
    }
    
    public boolean sendMessage(String message, long id) {
        SendMessage sendMessageRequest = new SendMessage();
        //Who should get from the message the sender that sent it.
        sendMessageRequest.setChatId(Long.toString(id)); 
        sendMessageRequest.setText(message);
        try {
            //At the end, so some magic and send the message ;)
            sendMessage(sendMessageRequest); 
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }
}
