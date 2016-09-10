import java.util.Map.Entry;

import org.telegram.telegrambots.api.objects.Message;

public class State6 {

	public static void init(AvalonBot bot, Game game) {
		bot.sendMessage("Please vote by typing /approve or /reject", game.gameId);
	}
	
	public static void receiveUpdate(AvalonBot bot, Game game, Message message) {
		Player currentPlayer = null;
		for (Player player : game.players) {
			if (player.id == message.getFrom().getId()) {
				currentPlayer = player;
			}
		}
		
		if (currentPlayer != null) {
			boolean validCommand = false;
			if (game.approveRejectMap.get(currentPlayer) == -1) {
				if (message.getText().equals("/approve") || message.getText().equals("/approve@AvalonBotBot")) {
					game.approveRejectMap.put(currentPlayer, 1);
					validCommand = true;
				} else if (message.getText().equals("/reject") || message.getText().equals("/reject@AvalonBotBot")) {
					game.approveRejectMap.put(currentPlayer, 0);
					validCommand = true;
				}
			}
			
			System.out.println(game.approveRejectMap);
			
			if (validCommand) {
				String approveOrReject = "";
				if (game.approveRejectMap.get(currentPlayer) == 1) {
					approveOrReject = "Approved";
				} else if (game.approveRejectMap.get(currentPlayer) == 0) {
					approveOrReject = "Rejected";
				}
				
				StringBuffer list = new StringBuffer();
				int numberOfVoters = 0;
				for (Entry<Player, Integer> entry : game.approveRejectMap.entrySet()) {
					//Count number of voters to proceed to next stage
					if (entry.getValue() != -1) {
						numberOfVoters++;
					}
					
					list.append(entry.getKey().name + " : " + (entry.getValue()!=-1? "Voted" : "Not Voted") + "\n");
				}
				bot.sendMessage(list.toString(), game.gameId);
				if (numberOfVoters >= game.players.size()) {
					//bot.sendMessage("I will now call state 7", game.gameId);
					list = new StringBuffer();
					for (Entry<Player, Integer> entry : game.approveRejectMap.entrySet()) {
						list.append(entry.getKey().name + " : " + (entry.getValue()==1? "Approved" : entry.getValue()==0? "Rejected" : "Not Voted") + "\n");
					}
					bot.sendMessage(list.toString(), game.gameId);
					game.state++;
					State7.run(game.approveRejectMap, bot, game, game.pendingMissionPlayers);
				}
			}
		}
	}
}
