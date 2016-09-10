
public class State10 {
    public static void checkGameStatus (AvalonBot bot, Game game) {
        if (game.successCount == 3) {  //good guys win, should now activate state 11
            
            bot.sendMessage("The loyal servants of Arthur has succeeded in 3 quests!", game.gameId);
            bot.sendMessage("The assasin will now be revealed.", game.gameId);
            bot.sendMessage("I will now call state 11.", game.gameId);

        } else if (game.failureCount == 3) {
            bot.sendMessage("The minions of Modred has failed 3 quests!", game.gameId);
            State12.endGame(game, bot);
        } else {
            bot.sendMessage("Current game status: ", game.gameId);
            bot.sendMessage("Loyal Servants of Arthur Wins: " + game.successCount, game.gameId);
            bot.sendMessage("Minions of Modred Wins: " + game.failureCount, game.gameId);
            
            bot.sendMessage("The next quest will begin shortly..", game.gameId);
            
            game.state = 4;
            State4.updateKing(bot, game);
        }
    }
}
