import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class AvalonBot extends TelegramLongPollingBot {

    public String token = "";
    public String botName = "";

    @Override
    public String getBotUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO Auto-generated method stub
        // check if the update has a message
        if (update.hasMessage()) {
            Message message = update.getMessage();

            // check if the message has text. it could also contain for example
            // a location ( message.hasLocation() )
            if (message.hasText()) {
            } else {
            }
        }
    }

    @Override
    public String getBotToken() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean sendMessage(String message, long id) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(Long.toString(id)); // who should get from
                                                         // the message the
                                                         // sender that sent it.
        sendMessageRequest.setText(message);
        try {
            sendMessage(sendMessageRequest); // at the end, so some magic and
                                             // send the message ;)
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

}
