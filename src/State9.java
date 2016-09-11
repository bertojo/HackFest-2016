import java.util.ArrayList;
import org.telegram.telegrambots.api.objects.Message;

public class State9 {
	public static boolean checkPlayer(ArrayList<Player> list, Long id) {
		for (Player player : list) {
			if (player.playerId == id) {
				return true;
			}
		}
		return false;
	}
	
	public static String checkRole(ArrayList<Player> list, Long id) {
		for (Player player : list) {
			if (player.playerId == id) {
				return player.role;
			}
		}
		return "";
	}

	public static void run(Message message, AvalonBot bot, Game game, Map map) {
		System.out.println(message.getText());
		int threshold = map.failureCount[map.currentQuestNumber];
        // Count the number of fail in the team
        if (checkPlayer(game.pendingMissionPlayers, message.getChatId())) {
        	if (message.getText().contains("success")) {
        		bot.sendMessage("Choice Accepted : SUCCESS!", message.getChatId());
        		game.successFailCount++;
        		bot.sendMessage("Waiting for mission players (" + game.missionFailCount + "/" +game.pendingMissionPlayers.size() + ")", game.gameId);
        	} else if (message.getText().contains("fail")) {
        		String role = checkRole(game.pendingMissionPlayers, message.getChatId());
        		if (role.equals(Roles.merlin) || role.equals(Roles.percival) || role.equals(Roles.goodling)) {
        			bot.sendMessage("You cheebye. You are a good man. You don't put fail", message.getChatId());
        			bot.sendMessage("I have chosen SUCCESS for you. KTHXBYE", message.getChatId());
        		} else {
	        		bot.sendMessage("Choice Accepted : FAIL!", message.getChatId());
	        		game.missionFailCount++;
        		}
        		game.successFailCount++;
        		bot.sendMessage("Waiting for mission players (" + game.missionFailCount + "/" +game.pendingMissionPlayers.size() + ")", game.gameId);
        	}
        	game.pmMissionCount ++;
        } else {
        	//bot.sendMessage("You don't cheebye.", message.getChatId());
        	bot.sendMessage("Sorry, you are not supposed to submit your choice to me!", message.getChatId());
        }
        
        if (game.pmMissionCount == game.pendingMissionPlayers.size()) {
        	// If number of fails is more than or equal to the threshold, mission has failed!
    		if (game.missionFailCount < threshold) {
    			bot.sendMessage("Mission SUCCESS!", game.gameId);
    			game.successCount++;
    		} else {
    			bot.sendMessage("Mission FAIL!", game.gameId);
    			game.failureCount++;
    		}
    		game.state++;
    		State10.checkGameStatus(bot, game);
        }
    }
}
