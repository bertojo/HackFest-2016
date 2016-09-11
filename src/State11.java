import org.telegram.telegrambots.api.objects.Message;

public class State11 {
    public static void run(AvalonBot bot, Game game) {
        //need to reveal Assassin
        if(game.successCount >= 3) { 
            //find the assassin in the arraylist 
            int i = 0;
            int indexOfAssassin = 0; 
            String character = "";
            while(!character.equals("Assassin")) { 
               character = game.players.get(i).role;
               indexOfAssassin = i;
               i++;
            }
            
            //assassin is found 
            bot.sendMessage(game.players.get(indexOfAssassin) + " is the Assassin! Please /choose someone to be Merlin and assassinate him!", game.gameId);
            
        } else { 
            bot.sendMessage("Loyal servants of Arthur has lost!", game.gameId);
            State12.endGame(game, bot);
        }
    }
    
    public static void chooseMerlin(AvalonBot bot, Game game, Message message) {
    	boolean success = false;
    	//Only handle assassin's command
    	for (Player player : game.players) {
    		if (message.getFrom().getId() == player.playerId && player.role.equals(Roles.assassin)) {
    			String chosen = message.getText().replace("/choose", "").trim(); //name of the chosen
    			//Check if player assassinated is merlin
    			for (Player loopPlayer : game.players) {
    				if (chosen.equals(loopPlayer.name) && loopPlayer.role.equals(Roles.merlin)) {
    					bot.sendMessage("Merlin has been assassinated!\n Minions of Mordered win!", game.gameId);
    					success = true;
    				}
    			}
    			
    			if (!success) {
    				bot.sendMessage("The assassination has failed to kill Merlin :(\n Loyal servants of Arthur has won!", game.gameId);
    			}
    			State12.endGame(game, bot);
    		}
    	}
    }

}
