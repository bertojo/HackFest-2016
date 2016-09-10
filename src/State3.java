import java.util.Collections;
import java.util.LinkedList;


//Randomly assign roles to players and tell them their allies
public class State3 {
    // assign roles to players
    public static void initialAssignment(Game game){
        LinkedList<Player> players = game.players;
        Collections.shuffle(players);
        String[] roles = Roles.getRoles(players.size());
        int i = 0;
        for(Player player : players){
            player.role = roles[i];
            i++;
        }
    }

}
