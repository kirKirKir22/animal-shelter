package pro.sky.telegrambotanimalshelter.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class UserInterface {

    private final TelegramBot telegramBot;

    public UserInterface(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendStageOneMessage(Message message) {
        long chatId = message.chat().id();
        String stageOneMessage = "Вы на этапе 1. Выберите, что вы хотите узнать о приюте:";
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Информация о приюте"),
                new KeyboardButton("Как взять животное из приюта"),
                new KeyboardButton("Прислать отчет о питомце"),
                new KeyboardButton("Позвать волонтера"));
        SendMessage response = new SendMessage(chatId, stageOneMessage).replyMarkup(keyboardMarkup);
        telegramBot.execute(response);
    }

    public void sendShelterInfo(Message message) {
        long chatId = message.chat().id();
        String shelterInfo = "Добро пожаловать в наш приют! Мы заботимся о бездомных животных. " +
                "Расписание работы: Пн-Пт: 9:00-18:00, Сб-Вс: 10:00-16:00.\n" +
                "Адрес: 123 Улица, Город, Страна.\n" +
                "Схема проезда: [ссылка на схему].\n" +
                "Контакты охраны: 123-456-789 (для оформления пропуска).\n" +
                "Общие рекомендации о технике безопасности на территории приюта: " +
                "пожалуйста, соблюдайте чистоту, не кормите животных, и т.д.";
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Принять и записать контактные данные"),
                new KeyboardButton("Вызвать волонтера"));
        SendMessage response = new SendMessage(chatId, shelterInfo).replyMarkup(keyboardMarkup);
        telegramBot.execute(response);
    }

    public void sendStageTwoMessage(Message message) {
        long chatId = message.chat().id();
        String stageTwoMessage = "Вы на этапе 2. Выберите, что вы хотите узнать о взятии животного из приюта:";
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Шаги по взятию питомца"),
                new KeyboardButton("Документы для взятия питомца"));
        SendMessage response = new SendMessage(chatId, stageTwoMessage).replyMarkup(keyboardMarkup);
        telegramBot.execute(response);
    }

    public void sendStageThreeMessage(Message message) {
        long chatId = message.chat().id();
        String stageThreeMessage = "Вы на этапе 3. Выберите, что вы хотите сделать с отчетом о питомце:";
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Создать отчет"),
                new KeyboardButton("Редактировать отчет"));
        SendMessage response = new SendMessage(chatId, stageThreeMessage).replyMarkup(keyboardMarkup);
        telegramBot.execute(response);
    }
}
