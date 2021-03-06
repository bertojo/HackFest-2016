import org.telegram.telegrambots.api.objects.Message;

//wait for user to join, maybe can include timer
public class State1 {
	public static void run(AvalonBot bot, Game game, Message message) {
		if (message.getText().equals("/join") || message.getText().equals("/join@AvalonBotBot")) {
			Player newPlayer = new Player(message.getFrom().getId(), message.getFrom().getFirstName());
			if (game.players.contains(newPlayer)) {
				String adminMsg = newPlayer.name + " has already joined the game (" + game.players.size() + " players)";
				bot.sendMessage(adminMsg, message.getChatId());
			} else {
				game.players.add(newPlayer);
				String adminMsg = "Added " + newPlayer.name + "! (" + game.players.size() + " players)";
				bot.sendMessage(adminMsg, message.getChatId());
			}
		} else if (message.getText().equals("/quit") || message.getText().equals("/quit@AvalonBotBot")) {
			Player quitter = new Player(message.getFrom().getId(), message.getFrom().getFirstName());
			if (game.players.remove(quitter)) {
				bot.sendMessage(quitter.name + " has left the game", message.getChatId());
			}
		} else if (message.getText().equals("/startgame") || message.getText().equals("/startgame@AvalonBotBot")) {
			if (game.players.size() < 5) {
				bot.sendMessage("Too little players! Please wait for more players to join! " + game.players.toString(), message.getChatId());
			} else if (game.players.size() > 11) {
				bot.sendMessage("Too many players! Please get some players to quit the game " + game.players.toString(), message.getChatId());
			} else {
				bot.sendMessage("Game is starting, please PM me \"/" + (game.gameId*-1) + "\" for your role and allies", message.getChatId());
				game.state++;
			}
		}
	}
}
