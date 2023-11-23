package pro.sky.telegrambotanimalshelter.service.implementation;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.constants.Constants;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.service.interfaces.ContactSharingService;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanCatService;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanDogService;

import java.util.Objects;

import static pro.sky.telegrambotanimalshelter.constants.Constants.USER_ADDED_PHONE_NUMBER_TO_DB;

@Service
public class ContactSharingServiceImpl implements ContactSharingService {
    private final HumanDogService humanDogService;
    private final HumanCatService humanCatService;
    private final TelegramBot telegramBot;

    @Autowired
    public ContactSharingServiceImpl(HumanDogService humanDogService, HumanCatService humanCatService, TelegramBot telegramBot) {
        this.humanDogService = humanDogService;
        this.humanCatService = humanCatService;
        this.telegramBot = telegramBot;
    }

    @Override
    public void shareContact(Update update) {
        if (update.message().contact() != null) {
            String firstName = update.message().contact().firstName();
            String lastName = update.message().contact().lastName();
            String phone = update.message().contact().phoneNumber();
            String username = update.message().chat().username();
            Long finalChatId = update.message().chat().id();
            var sortChatIdDog = humanDogService.getAllHumanDog().stream()
                    .filter(i -> Objects.equals(i.getChatId(), finalChatId)).toList();
            var sortChatIdCat = humanCatService.getAllHumanCat().stream()
                    .filter(i -> Objects.equals(i.getChatId(), finalChatId)).toList();
            if (!sortChatIdDog.isEmpty() || !sortChatIdCat.isEmpty()) {
                sendMessage(finalChatId, Constants.ALREADY_IN_DB.getValue());
                return;
            }
            if (lastName != null) {
                String name = firstName + " " + lastName + " " + username;
                if (humanCatService.findByChatId(finalChatId) == null) {
                    humanCatService.saveCat(new HumanCat(name, phone, finalChatId));
                } else {
                    humanCatService.updateHumanCat(new HumanCat(name, phone, finalChatId));
                }
                if (humanDogService.findByChatId(finalChatId) == null) {
                    humanDogService.saveDog(new HumanDog(name, phone, finalChatId));
                } else {
                    humanDogService.updateHumanDog(new HumanDog(name, phone, finalChatId));
                }
                sendMessage(finalChatId, Constants.ADD_TO_DB.getValue());
                return;
            }
            sendMessage(Constants.TELEGRAM_CHAT_VOLUNTEERS.getLongValue(), phone + " " + firstName + USER_ADDED_PHONE_NUMBER_TO_DB);
            sendForwardMessage(finalChatId, update.message().messageId());
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        telegramBot.execute(message);
    }

    private void sendForwardMessage(Long chatId, Integer messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(Constants.TELEGRAM_CHAT_VOLUNTEERS.getValue(), chatId, messageId);
        telegramBot.execute(forwardMessage);
    }
}