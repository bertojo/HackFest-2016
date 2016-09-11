
public class State10 {
    public static void checkGameStatus (AvalonBot bot, Game game) {
        if (game.successCount == 3) {  //good guys win, should now activate state 11
            
            bot.sendMessage("The loyal servants of Arthur has succeeded in 3 quests!\nThe assasin will now be revealed.", game.gameId);
            
            game.state++;
            State11.run(bot, game);

        } else if (game.failureCount == 3) {
            bot.sendMessage("The minions of Modred managed to fail 3 quests!", game.gameId);
            State12.endGame(game, bot);
        } else {
            String gameStatusMsg = "Current game status: \nLoyal Servants of Arthur Wins: " + game.successCount + "\nMinions of Modred Wins: " + game.failureCount;
            bot.sendMessage(gameStatusMsg, game.gameId);
            
            bot.sendMessage("The next quest will begin shortly..", game.gameId);
            
            game.state = 4;
            game.pendingMissionPlayers.clear();
            game.currentQuestNumber++;
            State4.updateKing(bot, game);
        }
    }
}
