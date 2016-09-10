import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

// Approve/Reject states
public class State7 {
    public static void run(String[] message, String[] users, AvalonBot bot, Game game, List<Player> pendingMission) {
        ArrayList<Integer> approve = new ArrayList<Integer>(), reject = new ArrayList<Integer>();
        for (int i = 0; i < message.length; i++) {
            if (message[i].equals("Approve")) {
                approve.add(i);
            } else {
                reject.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Approve:\n");
        for (int i = 0; i < approve.size(); i++) {
            sb.append(users[approve.get(i)]).append("\n");
        }
        sb.append("\nReject:\n");
        for (int i = 0; i < reject.size(); i++) {
            sb.append(users[reject.get(i)]).append("\n");
        }
        
        if (approve.size() - reject.size() > 0) {
            sb.append("\n\n").append("Outcome: <b>Approved</b>");
            bot.sendMessage(sb.toString(), game.gameId);
            Game.voteTrack = 0;
            State8.run(game, bot, pendingMission);
        } else {
            sb.append("\n\n").append("Outcome: <b>Rejected</b>");
            bot.sendMessage(sb.toString(), game.gameId);
            Game.voteTrack++;
            if (Game.voteTrack == 5) {
                //State11.lose(bot, game);
                // lose
            }
//            State4.run(); // incomplete
        }
    }
}

