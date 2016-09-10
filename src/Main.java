import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

public class Main {
        public static void main(String[] args) {

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new AvalonBot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }