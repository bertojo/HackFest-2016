import org.telegram.telegrambots.api.objects.Message;

public class State11 {
    public static void run(AvalonBot bot, Game game) {
        //need to reveal Assassin
        if(game.totalWinCount >= 3) {
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
            bot.sendMessage(game.players.get(indexOfAssassin) + " is the Assassin! Please choose someone to be Merlin and fuck him!", game.gameId);
            //need to insert keyboard here for the assassin to choose the character to be merlin 
        } else { 
            bot.sendMessage("LOYAL SERVANTS OF ARTHUR. YOU HAD ONE JOB AND YOU FUCKED UP.", game.gameId);
        }
    }

}
