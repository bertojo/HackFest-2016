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
            sb.append(Roles.roleDescription(player.role));
            if(!player.role.equals(Roles.goodling)){
                for (String ally : allies) {
                    for(Player person : players){
                        if(person.role.equals(ally)){
                            sb.append(person.name + " ");
                        }
                    }
                }
            }
            i++;
            bot.sendMessage(sb.toString(), player.id);
        }
        State4.updateKing(bot, game);
    }

}
