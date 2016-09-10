import org.telegram.telegrambots.api.objects.Message;

//wait for user to join, maybe can include timer
public class State1 {
	public static void run(AvalonBot bot, Game game, Message message) {
		if (message.getText().equals("/join")) {
			Player newPlayer = new Player(message.getFrom().getId(), message.getFrom().getFirstName());
			if (game.players.contains(newPlayer)) {
				String adminMsg = newPlayer.name + " has already joined the game";
				bot.sendMessage(adminMsg, message.getChatId());
			} else {
				game.players.add(newPlayer);
				String adminMsg = "Added " + newPlayer.name + "!";
				bot.sendMessage(adminMsg, message.getChatId());
			}
		} else if (message.getText().equals("/quit")) {
			Player quitter = new Player(message.getFrom().getId(), message.getFrom().getFirstName());
			if (game.players.remove(quitter)) {
				bot.sendMessage(quitter.name + " has left the game", message.getChatId());
			}
		} else if (message.getText().equals("/startgame")) {
			if (game.players.size() < 5) {
				bot.sendMessage("Too little players! Please wait for more players to join! " + game.players.toString(), message.getChatId());
			} else if (game.players.size() > 11) {
				bot.sendMessage("Too many players! Please get some players to quit the game " + game.players.toString(), message.getChatId());
			} else {
				bot.sendMessage("Game is starting, please PM me the gameId for your role and allies", message.getChatId());
				game.state++;
			}
		}
	}
}
