//Choose king
import java.util.Random;

public class State4 {
        
    public static void updateKing(AvalonBot bot ,Game game) {
        if (game.isFirstKing) {
            Random rand = new Random();
            int kingNum = rand.nextInt(game.players.size());
            game.isFirstKing = false;
            game.king = game.players.get(kingNum);
        } else {
            game.kingCounter++;
            game.king = game.players.get(game.kingCounter);
        }
        int numPlayersNeeded = game.map.missionPlayerCount[game.currentQuestNumber];
        String state4Msg = "The king is " + game.king.name + "!\nThe king shall now choose " + numPlayersNeeded + " players for the quest.\nPlease choose players in the following format: /choose playerName.";
        bot.sendMessage(state4Msg, game.gameId);
        
        game.state++;
    }
}
