import java.util.ArrayList;
import java.util.Collections;


//Randomly assign roles to players and tell them their allies
public class State3 {
    // assign roles to players
    public static void initialAssignment(Game game, AvalonBot bot){
        ArrayList<Player> players = game.players;
        Collections.shuffle(players);
        String[] roles = Roles.getRoles(players.size());
        int i = 0;
        for(Player player : players){
            player.role = roles[i];
            String[] allies = new String[4];
            allies = Roles.getAlly(player.role, players.size());
            StringBuilder sb = new StringBuilder();
            sb.append(Roles.roleDescription(player.role) + "\nYour allies are : ");
            for (String ally : allies) {
                sb.append(ally + " ");
            }
            i++;
            
            
            bot.sendMessage(sb.toString(), player.id);
        }
    }

}
