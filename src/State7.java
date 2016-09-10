import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.HashMap;

// Approve/Reject states
public class State7 {
    public static void run(HashMap<Player, Integer> usersVote, AvalonBot bot, Game game, List<Player> pendingMission) {
        ArrayList<String> approve = new ArrayList<String>(), reject = new ArrayList<String>();
        for (int i = 0; i < game.players.size(); i++) {
            if (usersVote.get(game.players.get(i).toString()) == 0) {
                reject.add(game.players.get(i).toString());
            } else if (usersVote.get(game.players.get(i).toString()) == 1) {
                approve.add(game.players.get(i).toString());
            } else {
                System.out.println("Vote == -1. Fix this error!");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<b>Approve:</b>\n");
        for (int i = 0; i < approve.size(); i++) {
            sb.append(approve.get(i)).append("\n");
        }
        sb.append("\n<b>Reject:</b>\n");
        for (int i = 0; i < reject.size(); i++) {
            sb.append(reject.get(i)).append("\n");
        }
        
        if (approve.size() - reject.size() > 0) {
            sb.append("\n\n").append("Outcome: <b>Approved</b>");
            bot.sendMessage(sb.toString(), game.gameId);
            game.voteTrack = 0;
            State8.run(game, bot, pendingMission);
        } else {
            sb.append("\n\n").append("Outcome: <b>Rejected</b>");
            bot.sendMessage(sb.toString(), game.gameId);
            game.voteTrack++;
            if (game.voteTrack == 5) {
                //State11.lose(bot, game);
                // lose
            }
            State4.updateKing(bot, game);
        }
    }
}

