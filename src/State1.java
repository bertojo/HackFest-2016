import org.telegram.telegrambots.api.objects.Message;

//wait for user to join, maybe can include timer
public class State1 {
	public static void run(Game game, Message message) {
		if (message.getText().equals("/join")) {
			Player newPlayer = new Player(message.getFrom().getId(), message.getFrom().getFirstName());
			game.players.add(newPlayer);
			System.out.println("Added " + newPlayer.name + " !");
		}
	}
}
