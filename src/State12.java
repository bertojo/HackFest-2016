import java.util.ArrayList;

//End Game, reveal all players on group chat
public class State12 {
    public static void endGame(Game game, AvalonBot bot){
        ArrayList<Player> players = game.players;
        StringBuilder sb = new StringBuilder();
        sb.append("The round of Avalon has ended. Listing Player Roles: \n");
        for(Player player : players){
            sb.append(player.name + ": " + player.role + "\n");
        }
        sb.append("\n\n Yay! Type /creategame to play again!");
        
        bot.sendMessage(sb.toString(), game.gameId);
    }

}
