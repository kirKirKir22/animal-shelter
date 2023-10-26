package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.UserMessageHandler;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final UserMessageHandler userMessageHandler;
    private boolean expectingContactInfo = false;

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
                } else if ("/record_contact_info".equals(messageText)) {
                    expectingContactInfo = true;
                    telegramBot.execute(new SendMessage(chatId, "Пожалуйста, введите свои контактные данные в формате: Имя +12345678901"));
                } else if (expectingContactInfo) {
                    userMessageHandler.saveNameAndPhoneUserForCatsShelter(messageText, chatId, telegramBot);
                    expectingContactInfo = false;
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
                } else if ("record_contact_info".equals(data)) {
                    expectingContactInfo = true;
                    telegramBot.execute(new SendMessage(chatId, "Пожалуйста, введите свои контактные данные в формате: Имя +12345678901"));
                } else {
                    userMessageHandler.handleInlineAction(chatId, data);
                }
            }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
