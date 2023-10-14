package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
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
            logger.info("Processing update: {}", update); // лог

            // Получаем текст сообщения, если оно есть
            String messageText = update.message() != null ? update.message().text() : "";

            // Проверяем, является ли сообщение командой "/start"
            if ("/start".equals(messageText)) {
                // Получаем идентификатор чата, чтобы отправить сообщение
                long chatId = update.message().chat().id();

                // Отправляем приветственное сообщение
                String welcomeMessage = "Добро пожаловать! Это телеграм-бот " + "Кошечки-собачки " + "от команды Red Apple";
                SendMessage response = new SendMessage(chatId, welcomeMessage); // Создаем сообщение для отправки
                telegramBot.execute(response); // Отправляем сообщение с помощью Telegram Bot API
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL; // Возвращаем статус подтверждения, что все обновления успешно обработаны
    }
}