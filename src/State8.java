public class State8 {
    
    public static void run(Game game, AvalonBot bot) {         
        for (int i = 0; i < game.pendingMissionPlayers.size(); i++) {
            bot.sendMessage("Type /"+(game.gameId*-1)+"_success or /"+(game.gameId*-1)+"_fail", game.pendingMissionPlayers.get(i).id);
        }
        game.state++;
        game.pmMissionCount = 0;
        game.missionFailCount = 0;
    }
        
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow test = new KeyboardRow();
//        test.add(new KeyboardButton("Success"));
//        test.add(new KeyboardButton("Failure"));
//        keyboard.add(test);
//        
//        ReplyKeyboardMarkup buttonReply = new ReplyKeyboardMarkup();
//        buttonReply.setSelective(true);
//        buttonReply.setResizeKeyboard(true);
//        buttonReply.setOneTimeKeyboad(true);
//        buttonReply.setKeyboard(keyboard);
//        
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setReplyMarkup(buttonReply);
//        try {
//            bot.sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } 
}
