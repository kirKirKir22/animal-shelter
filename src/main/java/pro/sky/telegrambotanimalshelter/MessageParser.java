package pro.sky.telegrambotanimalshelter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambotanimalshelter.models.UserForCatsShelter;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

    public static UserForCatsShelter parseMessage(String messageText, Long chatId, TelegramBot telegramBot) {
        if (messageText != null && !messageText.trim().isEmpty()) {
            Pattern PERSON_INFO_PATTERN = Pattern.compile("^(.+) (\\+\\d{11})$");
            Matcher matcher = PERSON_INFO_PATTERN.matcher(messageText);

            if (matcher.matches()) {
                String name = matcher.group(1);
                String phoneNumber = matcher.group(2);

                return new UserForCatsShelter(name, phoneNumber, chatId.toString()); // Преобразовываем chatId в строку
            }
        }

        telegramBot.execute(new SendMessage(chatId,
                "Ошибка: Сообщение не соответствует ожидаемому формату." +
                        " Пожалуйста, отправьте сообщение в следующем формате: Имя +12345678901"));
        return null;
    }
}
