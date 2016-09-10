// contains all the roles in the game
public class Roles {
    public static String percival = "Percival";
    public static String morgana = "Morgana";
    public static String merlin = "Merlin";
    public static String mordred = "Mordred";
    public static String assassin = "Assassin";
    public static String goodling = "Loyal Servant of Arthur";
    public static String badling = "Minion of Mordred";
    public final static int MIN_NUM = 5;
    public final static int MAX_NUM = 10;
    
    public static String[] getRoles(int numPlayers){
        // 5 - 3 g 2 b
        // 6 - 4 g 2 b
        // 7 - 4 g 3 b
        // 8 - 5 g 3 b
        // 9 - 6 g 3 b
        // 10 - 6 g 4 b
        String[] roles = new String[numPlayers];
        roles[0] = merlin;
        roles[1] = assassin;
        if(numPlayers == MIN_NUM){
            roles[2] = badling;
            roles[3] = goodling;
            roles[4] = goodling;
        } else if (numPlayers >= 6){
            roles[2] = percival;
            roles[3] = morgana;
            roles[4] = goodling;
            roles[5] = goodling;
            if(numPlayers >= 7){
                roles[6] = mordred;
                if(numPlayers >= 8){
                    roles[7] = goodling;
                    if(numPlayers >= 9){
                        roles[8] = goodling;
                        if(numPlayers == MAX_NUM){
                            roles[9] = badling;
                        }
                    }
                }
            }
        }
        return roles;
    }
    
    public static String roleDescription(String role){
        if(role == percival){
            return "You are Percival. Your main role is to prevent Merlin from getting fucked in the behind";
        } else if(role == morgana){
            return "You are Morgana. You gonna act like Merlin to try to fuck him in the behind";
        } else if(role == merlin){
            return "You are Merlin. You gonna lead the good guys to victory without getting fucked in the behind";
        } else if(role == mordred){
            return "You are Mordred. You gonna try fuck everyone in the behind";
        } else if(role == assassin){
            return "You are Assassin. You gonna try fuck Merlin in the behind";
        } else if(role == goodling){
            return "You are a Loyal Servant of Arthur. Nobody wanna fuck you in the behind";
        } else if (role == badling){
            return "You are a Minion of Mordred. You wanna fuck people in the behind but no power to fuck";
        } else {
            return "What are you? Fuck off pls";
        }
    }
}
