//Choose king
import java.util.Random;

public class State4 {
    
    public static boolean isFirstKing = true;
    
    public static void updateKing(AvalonBot bot ,Game game) {
        if (isFirstKing) {
            Random rand = new Random();
            int kingNum = rand.nextInt(game.players.size());
            isFirstKing = false;
            game.king = game.players.get(kingNum);
        } else {
            game.kingCounter++;
            game.king = game.players.get(game.kingCounter);
        }
        
        bot.sendMessage("The king is " + game.king.name + "!", game.gameId);
        bot.sendMessage("The king shall now choose " + game.map.missionPlayerCount[game.currentQuestNumber] + " players for the quest.", game.gameId);
        bot.sendMessage("Please choose players in the following format: /choose playerName.", game.gameId);
        game.state++;
    }
}
