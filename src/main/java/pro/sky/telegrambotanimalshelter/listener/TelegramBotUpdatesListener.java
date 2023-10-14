package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class); // лог

    @Autowired
    private TelegramBot telegramBot; // Инъекция зависимости TelegramBot

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);  // Устанавливаем этот класс в качестве слушателя для Telegram Bot API
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null) {
                long chatId = update.message().chat().id();
                String messageText = update.message().text();

                if ("/start".equals(messageText)) {
                    // Создаем инлайн-клавиатуру
                    InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                            new InlineKeyboardButton("Приют для кошек").callbackData("cat_shelter"),
                            new InlineKeyboardButton("Приют для собак").callbackData("dog_shelter"));

                    // Отправляем сообщение с клавиатурой
                    String welcomeMessage = "Добро пожаловать в приют для животных! Я помогу вам найти нового друга. Выберите, кого вы ищете:";
                    SendMessage response = new SendMessage(chatId, welcomeMessage).replyMarkup(markup);
                    telegramBot.execute(response);
                }
            }

            if (update.callbackQuery() != null) {
                long chatId = update.callbackQuery().message().chat().id();
                String data = update.callbackQuery().data();

                if ("cat_shelter".equals(data) || "dog_shelter".equals(data)) {
                    // Пользователь выбрал приют
                    String selectedShelter = "приют для " + (data.equals("cat_shelter") ? "кошек" : "собак");
                    String confirmationMessage = "Вы выбрали " + selectedShelter + ". Спасибо за ваш выбор!";
                    SendMessage response = new SendMessage(chatId, confirmationMessage);
                    telegramBot.execute(response);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}