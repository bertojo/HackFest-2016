//Choose king

public class State4 {
        
    public static void updateKing(AvalonBot bot ,Game game) {
        game.kingCounter++;
        bot.sendMessage("The king is " + game.players.get(game.kingCounter).name + "!", game.gameId);
        State5.kingChoosePlayers(bot, game);
    }
}
