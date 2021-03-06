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
    public String helpMessage = "Welcome to Avalon Bot!\nTo create a new game, type /creategame\nTo join an existing game, type /join\nTo start the game, type /startgame\nFor more information on roles, type /roles\n";
    public String roleMessage = "Roles:\nMerlin - Knows who the villians are, but must remain hidden\nPercival - Knows either Merlin or Morgana, must protect Merlin\nMorgana - Pretends to be Merlin\nAssassin - Must try to figure out and assassinate Merlin\nMordred - Hidden from Merlin\nLoyal Servant of Arther - Forces of Good\nMinion of Mordred - Forces of Evil";

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // check if the update has a message
         
        if (update.hasMessage()) {
        	System.out.println(update.getMessage());
            Message message = update.getMessage();
            if (message.hasText()) {
            	//Check if game has been create
            	long chatId = message.getChatId();
            	
            	//Check if the pm is the id or not
            	boolean isId = false;
            	try {
            		games.containsKey(Long.parseLong(message.getText().replace("/", "")));
            		isId = true;
            	} catch (Exception e) {
            		isId = false;
            	}
            	if(message.getText().equals("/closegame") || message.getText().equals("/closegame@AvalonBotBot")){
            	    if (games.containsKey(chatId)) {
            	    	games.remove(chatId);
            	    	sendMessage("Game has been closed. Type /creategame to start a new game!", chatId);
            	    } else {
            	    	sendMessage("No game found. Type /creategame to start a new game!", chatId);
            	    }
            	} else if(message.getText().equals("/help") || message.getText().equals("/help@AvalonBotBot")){
            	    this.sendMessage(helpMessage, message.getChatId());
            	} else if(message.getText().equals("/roles") || message.getText().equals("/roles@AvalonBotBot")){
            	    this.sendMessage(roleMessage, message.getChatId());
            	} else if (!games.containsKey(chatId) && isId) { //If a user pm the bot for their role, tell them individually and save that chatId to the user
            		State2.run(this, message, games);
            	} else if (message.getText().contains("_success") || message.getText().contains("_fail")) { //is a pm
            		long id = Long.parseLong(message.getText().replace("/", "").replace("_success", "").replace("_fail", "")) * -1;
            		Game currentGame = games.get(id);
            		System.out.println(currentGame.state);
            		if (currentGame.state == 9) {
            		    State9.run(message, this, currentGame, currentGame.map);
            		}
            	} else if ((message.getText().equals("/creategame") || message.getText().equals("/creategame@AvalonBotBot")) && !games.containsKey(chatId)) {
            		//Create a new game
            		Game game = new Game(chatId);
            		games.put(chatId, game);
            		sendMessage("Game Created, waiting for players (Type /join to join the game)", chatId);
            	} else if (games.containsKey(chatId)){
            		//Handle the game based on its state
            		Game game = games.get(chatId);
            		
            		//Do if else statements to handle the state here which are waiting for input
            		System.out.println("Game State: " + game.state);
            		if (game.state == 1) { //Handle player joining games
            			State1.run(this, game, message);
            		} else if (game.state == 5) {
            		    State5.receiveUpdate(this, game, message);
            		} else if (game.state == 6) {
            			State6.receiveUpdate(this, game, message);
            		} else if (game.state == 11) {
            			State11.chooseMerlin(this, game, message);
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
