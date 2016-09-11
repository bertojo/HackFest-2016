import java.util.ArrayList;
import java.util.HashMap;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardHide;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class State5 {
    
    public static void kingChoosePlayers(AvalonBot bot, Game game) {
        Player king = game.king;
        int numOfQuesters = game.map.missionPlayerCount[game.currentQuestNumber];
        int numChosen = 0;
        bot.sendMessage("The king shall now choose who will go on the quest.", game.gameId);
        ReplyKeyboardMarkup specialKeyboard = getQuestChoosingKeyboard(bot, game);
        SendMessage keyboardMessage = new SendMessage();
        String gameID = new String("" + game.gameId + "");
        keyboardMessage.setChatId(gameID);
        keyboardMessage.enableMarkdown(true);
        keyboardMessage.setReplyMarkup(specialKeyboard);
        bot.sendMessage("Please choose " + numOfQuesters + " players.", king.id);
        
        try{
            bot.sendMessage(keyboardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
        if (numChosen == numOfQuesters) {
            bot.sendMessage("King has chosen his Knights.", game.gameId);
            SendMessage closeKeyboardMessage = new SendMessage();
            closeKeyboardMessage.setChatId(gameID);
            closeKeyboardMessage.enableMarkdown(true);

            ReplyKeyboardHide replyKeyboardHide = new ReplyKeyboardHide();
            replyKeyboardHide.setSelective(true);
            closeKeyboardMessage.setReplyMarkup(replyKeyboardHide);
        } else {
            
        }
    }
    
    
    
    public static void receiveUpdate (AvalonBot bot, Game game, Message msg) {
        Player king = game.king;
        int numOfQuesters = game.map.missionPlayerCount[game.currentQuestNumber];
        int remainder = numOfQuesters - game.numChosen;

        if (msg.getFrom().getFirstName().equals(king.name)) {
            
            String msgUpdate = msg.getText();
            String prefix = msgUpdate.trim().substring(0, 7);
            String chooseWho = msgUpdate.trim().substring(8, msgUpdate.length()).trim();
            Player playerChosen = findPlayer (game, chooseWho);
            if (prefix.equals("/choose")) {
                if(!playerChosen.equals(null)){
                    String chooseHowManyMorePplMsg = "The king shall now choose " + (remainder-1) + " more players for the quest.\nPlease choose players in the following format: /choose playerName.";
                    bot.sendMessage(chooseHowManyMorePplMsg, game.gameId);
                
                    if (game.pendingMissionPlayers.contains(playerChosen)) {
                        bot.sendMessage("This player has already been chosen.", game.gameId);
                    } else {
                        game.pendingMissionPlayers.add(playerChosen);
                        bot.sendMessage(chooseWho + " has been chosen!", game.gameId);
                        game.numChosen++;
                    }
                } else {
                    bot.sendMessage("Player not found", game.gameId);
                }
                
                //next state if fixed
                if (game.numChosen == numOfQuesters) {
                    bot.sendMessage("Players chosen are the following: ", game.gameId);
                    printList(bot, game);
                    game.numChosen = 0;
                    
                    game.state++;
                    game.approveRejectMap = new HashMap<Player, Integer>();
                    for (Player player : game.players) {
                    	game.approveRejectMap.put(player, -1);
                    }
                    State6.init(bot, game);
                }
            } else if (prefix.equals("/remove")) {
            	if(!playerChosen.equals(null)){
                    bot.sendMessage("The king shall now choose " + (remainder+1) + " more players for the quest.", game.gameId);
                    bot.sendMessage("Please choose players in the following format: /choose playerName.", game.gameId);
                
                    if (!game.pendingMissionPlayers.contains(playerChosen)) {
                        bot.sendMessage("THIS GUY IS NOT IN THE TEAM LAH.", game.gameId);
                    } else {
                        game.pendingMissionPlayers.remove(playerChosen);
                        bot.sendMessage(chooseWho + " has been removed.", game.gameId);
                        game.numChosen--;
                    }
                } else {
                    bot.sendMessage("Player not found", game.gameId);
                }
            }
        } // else ignore
    }
    
    public static void printList(AvalonBot bot, Game game) {
        StringBuilder allChosen = new StringBuilder();
        for (int i = 0; i < game.pendingMissionPlayers.size(); i++) {
            allChosen.append(game.pendingMissionPlayers.get(i).name + "\n");
        }
        allChosen.append("It is time to vote if mission is a go.");
        bot.sendMessage(allChosen.toString(), game.gameId);
    }
    
    public static Player findPlayer (Game game, String targetName) {
        Player foundPlayer = null;
        for (int i = 0; i < game.players.size(); i++) {
            if (game.players.get(i).name.equals(targetName)) {
                foundPlayer = game.players.get(i);
            }
        }
        return foundPlayer;
    }

    public static ReplyKeyboardMarkup getQuestChoosingKeyboard(AvalonBot bot, Game game) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(false);

        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        for (int i = 0; i < game.players.size(); i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(game.players.get(i).name));
            keyboardRow.add(new KeyboardButton("Approve"));
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

}
