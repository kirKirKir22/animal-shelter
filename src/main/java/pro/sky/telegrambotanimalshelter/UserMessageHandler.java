package pro.sky.telegrambotanimalshelter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class UserMessageHandler {

    private final TelegramBot telegramBot;

    public UserMessageHandler(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendStartMessage(long chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Приют для кошек").callbackData("cat_shelter"),
                new InlineKeyboardButton("Приют для собак").callbackData("dog_shelter"));

        String welcomeMessage = "Добро пожаловать в приют для животных! Я помогу вам найти нового друга. Выберите, кого вы ищете:";
        SendMessage response = new SendMessage(chatId, welcomeMessage).replyMarkup(markup);
        telegramBot.execute(response);
    }

    public void sendStageOneButtonsCat(long chatId) {
        // Приветственное сообщение от приюта для кошек
        String welcomeMessage = "Добро пожаловать в приют для кошек! Как мы можем вам помочь? Выберите действие:";
        SendMessage welcomeResponse = new SendMessage(chatId, welcomeMessage);
        telegramBot.execute(welcomeResponse);

        // Создаем инлайн-клавиатуру с кнопками
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Узнать информацию о приюте (этап 1)").callbackData("stage_1_info"),
                new InlineKeyboardButton("Как взять животное из приюта (этап 2)").callbackData("stage_2_adopt"),
                new InlineKeyboardButton("Прислать отчет о питомце (этап 3)").callbackData("stage_3_report"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("call_volunteer"),
                new InlineKeyboardButton("Назад").callbackData("back"));

        SendMessage response = new SendMessage(chatId, "Выберите, что вы хотите узнать о приюте:").replyMarkup(markup);
        telegramBot.execute(response);
    }

    public void sendStageOneButtonsDog(long chatId) {
        // Приветственное сообщение от приюта для собак
        String welcomeMessage = "Добро пожаловать в приют для собак! Как мы можем вам помочь? Выберите действие:";
        SendMessage welcomeResponse = new SendMessage(chatId, welcomeMessage);
        telegramBot.execute(welcomeResponse);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Узнать информацию о приюте (этап 1)").callbackData("stage_1_info"),
                new InlineKeyboardButton("Как взять животное из приюта (этап 2)").callbackData("stage_2_adopt"),
                new InlineKeyboardButton("Прислать отчет о питомце (этап 3)").callbackData("stage_3_report"),
                new InlineKeyboardButton("Позвать волонтера").callbackData("call_volunteer"),
                new InlineKeyboardButton("Назад").callbackData("back"));

        SendMessage response = new SendMessage(chatId, "Выберите, что вы хотите узнать о приюте:").replyMarkup(markup);
        telegramBot.execute(response);
    }

    public void sendCatShelterInformation(long chatId, String shelterType) {
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

    public void sendDogShelterInformation(long chatId, String shelterType) {
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

    public void handleInlineAction(long chatId, String action) {
        if ("stage_1_info".equals(action)) {
            sendCatShelterInformation(chatId, "приют для кошек");
        } else if ("stage_2_adopt".equals(action)) {
            // Обработка действия "Как взять животное из приюта (этап 2)"
            // Реализуйте соответствующую логику.
        } else if ("stage_3_report".equals(action)) {
            // Обработка действия "Прислать отчет о питомце (этап 3)"
            // Реализуйте соответствующую логику.
        } else if ("call_volunteer".equals(action)) {
            // Обработка действия "Позвать волонтера"
            // Реализуйте соответствующую логику.
        } else if ("back".equals(action)) {
            // Обработка действия "Назад"
            sendStartMessage(chatId);
        }
    }

}
