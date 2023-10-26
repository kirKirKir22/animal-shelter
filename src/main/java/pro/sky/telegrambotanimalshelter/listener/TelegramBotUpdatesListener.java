package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.UserMessageHandler;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private TelegramBot telegramBot;
    private UserMessageHandler userMessageHandler;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, UserMessageHandler userMessageHandler) {
        this.telegramBot = telegramBot;
        this.userMessageHandler = userMessageHandler;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            if (update.message() != null) {
                long chatId = update.message().chat().id();
                String messageText = update.message().text();

                if ("/start".equals(messageText)) {
                    userMessageHandler.sendStartMessage(chatId);
                }
            }

            if (update.callbackQuery() != null) {
                long chatId = update.callbackQuery().message().chat().id();
                String data = update.callbackQuery().data();

                if ("cat_shelter".equals(data)) {
                    userMessageHandler.sendStageOneButtonsCat(chatId);
                } else if ("dog_shelter".equals(data)) {
                    userMessageHandler.sendStageOneButtonsDog(chatId);
                } else if ("back".equals(data)) {
                    userMessageHandler.sendStartMessage(chatId);
                } else if ("call_volunteer".equals(data)) {
                    userMessageHandler.sendCallVolunteerMessage(chatId);
                } else {
                    userMessageHandler.handleInlineAction(chatId, data);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
