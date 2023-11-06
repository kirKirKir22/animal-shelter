package pro.sky.telegrambotanimalshelter.keyboard;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.constants.Constants;

@Service
public class HotkeysShelter {

    private final com.pengrad.telegrambot.TelegramBot telegramBot;

    private static final Logger logger = LoggerFactory.getLogger(HotkeysShelter.class);

    public HotkeysShelter(com.pengrad.telegrambot.TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Метод для отправки главного меню
    public void sendMenu(long chatId) {
        logger.info("Starting main menu: {}, {}", chatId, "Вызвано основное меню");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(Constants.INFO_ABOUT_BOT_BUTTON.getValue()),
                new KeyboardButton(Constants.SHELTER_INFO_MENU.getValue()));
        replyKeyboardMarkup.addRow(new KeyboardButton(Constants.HOW_GET_ANIMAL.getValue()),
                new KeyboardButton(Constants.SEND_REPORT.getValue()));
        replyKeyboardMarkup.addRow(new KeyboardButton(Constants.SEND_MESSAGE_VOLUNTEER.getValue()));

        keyboardUpdate(replyKeyboardMarkup);

        SendMessage request = new SendMessage(chatId, Constants.WELCOME.getValue())
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("error code: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    // Метод для отправки меню с информацией о приюте
    public void sendMenuInfoShelter(long chatId) {
        logger.info("Starting menu information about animal shelter: {}, {}", chatId, "Вызвано меню: Информация о приюте");

        ReplyKeyboardMarkup replyKeyboardMarkup2 = new ReplyKeyboardMarkup(new KeyboardButton(Constants.ABOUT_ANIMAL_SHELTER.getValue()),
                new KeyboardButton(Constants.GET_USER_CONTACT.getValue()).requestContact(true))
                .addRow(new KeyboardButton(Constants.DRIVER_SCHEME.getValue()), new KeyboardButton(Constants.FOR_SAFETY.getValue()));
        repeatableMenu(chatId, replyKeyboardMarkup2);
    }

    // Вспомогательный метод для создания меню
    private void repeatableMenu(long chatId, ReplyKeyboardMarkup replyKeyboardMarkup2) {
        replyKeyboardMarkup2.addRow(new KeyboardButton(Constants.SEND_MESSAGE_VOLUNTEER.getValue()),
                new KeyboardButton(Constants.RETURN_MENU.getValue()));

        keyboardUpdate(replyKeyboardMarkup2);

        SendMessage request = new SendMessage(chatId, Constants.FIND_INFORMATION.getValue())
                .replyMarkup(replyKeyboardMarkup2)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("error code: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    // Метод для отправки меню о том, как взять животное из приюта
    public void sendMenuTakeAnimal(long chatId) {
        logger.info("Starting menu about taking animal from shelter: {}, {}", chatId, "меню: Как взять питомца из приюта");

        ReplyKeyboardMarkup replyKeyboardMarkup3 = new ReplyKeyboardMarkup(new KeyboardButton(Constants.TIPS_AND_RECOMMENDATIONS.getValue()),
                new KeyboardButton(Constants.GET_USER_CONTACT.getValue()))
                .addRow(new KeyboardButton(Constants.DOCUMENTS.getValue()), new KeyboardButton(Constants.GET_ANIMAL_WITH_DEFECTS.getValue()));
        repeatableMenu(chatId, replyKeyboardMarkup3);
    }

    // Метод для выбора типа животного (кот или собака)
    public void chooseMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано меню выбора ");

        String cat = Constants.CAT.getValue();
        String dog = Constants.DOG.getValue();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(cat));
        replyKeyboardMarkup.addRow(new KeyboardButton(dog));
        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, Constants.CHOOSE_PET.getValue());
    }

    // Вспомогательный метод для отправки сообщений с клавиатурой
    public void returnResponseReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String text) {
        keyboardUpdate(replyKeyboardMarkup);

        SendMessage request = new SendMessage(chatId, text)
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);

        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }

    // Метод для настройки клавиатуры
    private static void keyboardUpdate(ReplyKeyboardMarkup replyKeyboardMarkup) {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
    }
}