import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.HashMap;

// Approve/Reject states
public class State7 {
    public static void run(HashMap<Player, Integer> usersVote, AvalonBot bot, Game game, List<Player> pendingMission) {
        ArrayList<String> approve = new ArrayList<String>(), reject = new ArrayList<String>();
        for (int i = 0; i < game.players.size(); i++) {
            if (usersVote.get(game.players.get(i)) == 0) {
                reject.add(game.players.get(i).toString());
            } else if (usersVote.get(game.players.get(i)) == 1) {
                approve.add(game.players.get(i).toString());
            } else {
                System.out.println("Vote == -1. Fix this error!");
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if (approve.size() - reject.size() > 0) {
            sb.append("\n\n").append("Approved");
            bot.sendMessage(sb.toString(), game.gameId);
            game.voteTrack = 0;
            game.state++;
            game.successFailCount = 0;
    		bot.sendMessage("Waiting for mission players (" + (game.successFailCount) + "/" +game.pendingMissionPlayers.size() + ")", game.gameId);
            State8.run(game, bot);
        } else {
            sb.append("\n\n").append("Rejected");
            bot.sendMessage(sb.toString(), game.gameId);
            game.voteTrack++;
            if (game.voteTrack == 5) {
                //State11.lose(bot, game);
                // lose
            	game.failureCount++;
            	game.map.currentQuestNumber++;
            	game.voteTrack = 0;
            	bot.sendMessage("Evil wins since everyone rejected 5 times GG", game.gameId);
            	game.state = 12;
            	State12.endGame(game, bot);
            } else {
            	game.state = 4;
            	game.pendingMissionPlayers.clear();
            	game.numChosen = 0;
            	State4.updateKing(bot, game);
            }
            
        }
    }
}

