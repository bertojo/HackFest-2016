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
            game.kingCounter = (game.kingCounter + 1) % game.players.size();
            game.king = game.players.get(game.kingCounter);
        }
        int numPlayersNeeded = game.map.missionPlayerCount[game.currentQuestNumber];
        String state4Msg = String.format("Current quest number is %d\nThe King is %s!\nThe King shall now choose %d players for the quest.\nPlease choose players in the following format: /choose playerName.",
        		game.currentQuestNumber+1,
        		game.king.name,
        		numPlayersNeeded);
        bot.sendMessage(state4Msg, game.gameId);
        game.state++;
    }
}
