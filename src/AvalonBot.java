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
            	
            	//
            	boolean isId = false;
            	try {
            		games.containsKey(Long.parseLong(message.getText().replace("/", "")));
            		isId = true;
            	} catch (Exception e) {
            		isId = false;
            	}
            	//If a user pm the bot for their role, tell them individually and save that chatId to the user
            	if (!games.containsKey(chatId) && isId) {
            		//Tell users their role if they pm the bot the gameid for their role
            		sendMessage("You are a bitch", chatId);
            		Game game = games.get(Long.parseLong(message.getText().replace("/", "")));
            		for (Player player : game.players) {
            			if (player.name.equals(message.getFrom().getFirstName())) {
            				player.id = message.getChatId();
            			}
            		}
            	} else if (message.getText().equals("/creategame") && !games.containsKey(chatId)) {
            		//Create a new game
            		Game game = new Game(chatId);
            		games.put(chatId, game);
            		sendMessage("Game Created, waiting for players", chatId);
            	} else if (games.containsKey(chatId)){
            		//Handle the game based on its state
            		Game game = games.get(chatId);
            		
            		//Do if else statements to handle the state here which are waiting for input
            		if (game.state == 1) { //Handle player joining games
            			State1.run(this, game, message);
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
