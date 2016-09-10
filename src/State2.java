import java.util.TreeMap;
import org.telegram.telegrambots.api.objects.Message;

//Start game
public class State2 {

	public static void run(AvalonBot bot, Message message, TreeMap<Long, Game> games) {
		//Tell users their role if they pm the bot the gameid for their role
		Game game = games.get(Long.parseLong(message.getText().replace("/", "")));
		if (game.state == 2) {
			bot.sendMessage("Connection Establish! Please wait for the other players to establish the connection", message.getChatId());
			for (Player player : game.players) {
				if (player.name.equals(message.getFrom().getFirstName())) {
					if (player.id != 0) {
						player.id = message.getChatId();
						bot.sendMessage("Established connection with " + player.name + " (" + (game.pmChannelCount+1) + "/" + game.players.size() + ")", game.gameId);
						if (++game.pmChannelCount == game.players.size()) {
							game.state++;
							bot.sendMessage("All PM Channels successfully established! Starting game now!", game.gameId);
							State3.initialAssignment(game, bot);
						}
					}
					
				}
			}
		}
	}
}
