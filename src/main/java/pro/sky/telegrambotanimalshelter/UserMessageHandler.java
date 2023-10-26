package pro.sky.telegrambotanimalshelter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambotanimalshelter.models.UserForCatsShelter;
import pro.sky.telegrambotanimalshelter.repository.UserForCatsShelterRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.UserForCatsShelterService;

import static pro.sky.telegrambotanimalshelter.MessageParser.parseMessage;

@Component
public class UserMessageHandler {

    private final TelegramBot telegramBot;
    private UserForCatsShelterRepository userForCatsShelterRepository;

    public UserMessageHandler(TelegramBot telegramBot, UserForCatsShelterRepository userForCatsShelterRepository) {
        this.telegramBot = telegramBot;
        this.userForCatsShelterRepository = userForCatsShelterRepository;
    }

    public void sendStartMessage(long chatId) { // выбор между приютами
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Приют для кошек").callbackData("cat_shelter"),
                new InlineKeyboardButton("Приют для собак").callbackData("dog_shelter"));

        String welcomeMessage = "Добро пожаловать в приют для животных! Я помогу вам найти нового друга. Выберите, кого вы ищете:";
        SendMessage response = new SendMessage(chatId, welcomeMessage).replyMarkup(markup);
        telegramBot.execute(response);
    }

    public void sendStageOneButtonsCat(long chatId) { //подменю кошачьего приюта
        // Приветственное сообщение от приюта для кошек
        String welcomeMessage = "Добро пожаловать в приют для кошек! Как мы можем вам помочь? Выберите действие:";
        SendMessage welcomeResponse = new SendMessage(chatId, welcomeMessage);
        telegramBot.execute(welcomeResponse);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Узнать информацию о приюте (этап 1)").callbackData("stage_1_info"),
                new InlineKeyboardButton("Как взять животное из приюта (этап 2)").callbackData("stage_2_adopt"),
                new InlineKeyboardButton("Прислать отчет о питомце (этап 3)").callbackData("stage_3_report"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("call_volunteer"),
                new InlineKeyboardButton("Записать контактные данные").callbackData("record_contact_info"),
                new InlineKeyboardButton("Назад").callbackData("back"));

        SendMessage response = new SendMessage(chatId, "Выберите, что вы хотите узнать о приюте:").replyMarkup(markup);
        telegramBot.execute(response);
    }

    public void sendStageOneButtonsDog(long chatId) { //подменю собачьего приюта
        // Приветственное сообщение от приюта для собак
        String welcomeMessage = "Добро пожаловать в приют для собак! Как мы можем вам помочь? Выберите действие:";
        SendMessage welcomeResponse = new SendMessage(chatId, welcomeMessage);
        telegramBot.execute(welcomeResponse);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Узнать информацию о приюте (этап 1)").callbackData("stage_1_info"),
                new InlineKeyboardButton("Как взять животное из приюта (этап 2)").callbackData("stage_2_adopt"),
                new InlineKeyboardButton("Прислать отчет о питомце (этап 3)").callbackData("stage_3_report"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("call_volunteer"),
                new InlineKeyboardButton("Записать контактные данные").callbackData("record_contact_info"),
                new InlineKeyboardButton("Назад").callbackData("back"));

        SendMessage response = new SendMessage(chatId, "Выберите, что вы хотите узнать о приюте:").replyMarkup(markup);
        telegramBot.execute(response);
    }

    public void sendCatShelterInformation(long chatId, String shelterType) { // реакция на кнопку-Узнать информацию о приюте кошек (этап 1)
        String shelterInfoMessage = "Бот приветствует пользователя.\n"
                + "Бот может рассказать о приюте.\n"
                + "Бот может выдать расписание работы приюта и адрес, схему проезда.\n"
                + "Бот может выдать контактные данные охраны для оформления пропуска на машину.\n"
                + "Бот может выдать общие рекомендации о технике безопасности на территории приюта.";

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Назад").callbackData("back"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("call_volunteer")
        );

        SendMessage infoMessage = new SendMessage(chatId, shelterInfoMessage).replyMarkup(markup);
        telegramBot.execute(infoMessage);
    }

    public void sendDogShelterInformation(long chatId, String shelterType) { // реакция на кнопку-Узнать информацию о приюте собак (этап 1)
        String shelterInfoMessage = "Бот приветствует пользователя.\n"
                + "Бот может рассказать о приюте.\n"
                + "Бот может выдать расписание работы приюта и адрес, схему проезда.\n"
                + "Бот может выдать контактные данные охраны для оформления пропуска на машину.\n"
                + "Бот может выдать общие рекомендации о технике безопасности на территории приюта.";

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Назад").callbackData("back"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("call_volunteer")
        );

        SendMessage infoMessage = new SendMessage(chatId, shelterInfoMessage).replyMarkup(markup);
        telegramBot.execute(infoMessage);
    }

    public void sendCallVolunteerMessage(long chatId) { // кнопка-позвать волонтёра
        String callVolunteerMessage = "Волонтёр скоро с вами свяжется";
        SendMessage response = new SendMessage(chatId, callVolunteerMessage);
        telegramBot.execute(response);
    }

    public void saveNameAndPhoneUserForCatsShelter(String messageText, Long chatId, TelegramBot telegramBot) {
        UserForCatsShelter user = parseMessage(messageText, chatId, telegramBot);
        if (user != null) {
            userForCatsShelterRepository.save(user);
        }

    }


    public void handleInlineAction(long chatId, String action) { // обработчик действий
        if ("stage_1_info".equals(action)) {
            sendCatShelterInformation(chatId, "приют для кошек");
        } else if ("stage_2_adopt".equals(action)) {
        } else if ("stage_3_report".equals(action)) {
        } else if ("call_volunteer".equals(action)) {
            sendCallVolunteerMessage(chatId);
        } else if ("back".equals(action)) {
            sendStartMessage(chatId);
        }
    }


}
