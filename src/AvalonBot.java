import java.util.TreeMap;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class AvalonBot extends TelegramLongPollingBot {

    public String token = "209510292:AAGF3xpH2h5z7FmXJO1OdsFv_QjL-sx6ITA";
    public String botName = "AvalonBotBot";
    public TreeMap<Long, Game> games = new TreeMap<Long, Game>();

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // check if the update has a message
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
            	//Check if game has been create
            	long chatId = message.getChatId();
            	if (!games.containsKey(chatId)) {
            		//Create a new game
            		Game game = new Game(chatId);
            		games.put(chatId, game);
            		System.out.println("Game Created, waiting for players");
            	} else {
            		//Handle the game based on its state
            		Game game = games.get(chatId);
            		//Do if else statements to handle the state here which are waiting for input
            		if (game.state == 1) { //Handle player joining games
            			State1.run(game, message);
            		}
            	}
            	
            }
        }
    }

    @Override
    public String getBotToken() {
        return token;
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
