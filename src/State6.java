import org.telegram.telegrambots.api.objects.Message;

public class State6 {
    public static void run(Game game) {
        //Approve/Reject for everyone 
        
        //need to get everyone to approve and reject
        //hence the special keyboard is for everyone
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"Approve"},
                new String[]{"Reject"})
                .oneTimeKeyboard(true)   // optional
                .resizeKeyboard(true)    // optional
                .selective(true);        // optional
        
//        // keyboard button
//        Keyboard keyboard = new ReplyKeyboardMarkup(
//                new KeyboardButton[]{
//                    new KeyboardButton("text"),
//                    new KeyboardButton("contact").requestContact(true),
//                    new KeyboardButton("location").requestLocation(true)
//                }
//        );                
//        
//        Keyboard forceReply = new ForceReply(isSelective); // or just new ForceReply();
//        Keyboard replyKeyboardHide = new ReplyKeyboardHide(); // new ReplyKeyboardHide(isSelective)
//        
//        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
//                new InlineKeyboardButton[]{
//                    new InlineKeyboardButton("url").url("url"),
//                    new InlineKeyboardButton("callback_data").callbackData("callback_data"),
//                    new InlineKeyboardButton("switch_inline_query").switchInlineQuery("switch_inline_query")
            });

    }
}
