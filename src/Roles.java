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
        // NEED TO REFACTOR TO BE LESS RUDE :(
        if(role.equals(percival)){
            return "You are Percival. Your goal is to try to protect the Merlin! \n"
                    + "Merlin will either be: \n";
        } else if(role.equals(morgana)){
            return "You are Morgana. Pretend to be like Merlin to trick Percival onto your side! \n"
                    + "The villians this game will be: \n";
        } else if(role.equals(merlin)){
            return "You are Merlin. Try to win the game without revealing yourself! \n"
                    + "The villians this game will be (minus Mordred if he is in): \n";
        } else if(role.equals(mordred)){
            return "You are Mordred. Merlin doesn't know who you are, make use of that fact to make the villians win! \n"
                    + "The villians this game will be: \n";
        } else if(role.equals(assassin)){
            return "You are Assassin. Try to assassinate Merlin! \n"
                    + "The villians this game will be: \n";
        } else if(role.equals(goodling)){
            return "You are a Loyal Servant of Arthur. Use the power of democracy to make the good guys win! \n";
        } else if (role.equals(badling)){
            return "You are a Minion of Mordred. Hide yourself amongst the Loyal Servants and make sure they lose! \n"
                    + "The villians this game will be: \n";
        } else {
            return "This message should not be appearing. Sorry there seems to be a bug. D:";
        }
    }
    
    private static String[] getBadGuys(String role, int numPlayers){
        String[] ally = new String[4];
        if(numPlayers == MIN_NUM){
            ally[0] = assassin;
            ally[1] = badling;
        } else if (numPlayers >= 6){
            ally[0] = assassin;
            ally[1] = morgana;
        } else if(numPlayers >= 9){
            ally[0] = assassin;
            ally[1] = morgana;
            if(!role.equals(merlin)){
                ally[2] = mordred;
            }
        } else if(numPlayers == MAX_NUM){
            ally[0] = assassin;
            ally[1] = morgana;
            ally[2] = badling;
            if(!role.equals(merlin)){
                ally[3] = mordred;
            }
        }
        return ally;
    }
    
    public static String[] getAlly(String role, int numPlayers){
        String[] ally = new String[3];
        // 5 - 3 g 2 b
        // 6 - 4 g 2 b
        // 7 - 4 g 3 b
        // 8 - 5 g 3 b
        // 9 - 6 g 3 b
        // 10 - 6 g 4 b
        // doing it for the bad guys

        if(role == percival){
            if(Math.random()<0.5){
                ally[0] = merlin;
                ally[1] = morgana;
            } else {
                ally[1] = merlin;
                ally[0] = morgana;
            }
        } else if(role == goodling){
            ;
        } else{
           ally = getBadGuys(role, numPlayers);
        }
        return ally;
    }
}
