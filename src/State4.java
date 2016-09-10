//Choose king

public class State4 {
        
    public static void updateKing(AvalonBot bot ,Game game) {
        game.kingCounter++;
        State5.kingChoosePlayers(bot, game);
    }
}
