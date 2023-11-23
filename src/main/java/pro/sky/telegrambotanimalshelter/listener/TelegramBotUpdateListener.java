package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambotanimalshelter.constants.Constants;
import pro.sky.telegrambotanimalshelter.handlers.ReportHandler;
import pro.sky.telegrambotanimalshelter.keyboard.HotkeysShelter;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.service.interfaces.*;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static pro.sky.telegrambotanimalshelter.constants.Constants.HI;
import static pro.sky.telegrambotanimalshelter.constants.Constants.getEnum;

@Component
public class TelegramBotUpdateListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final ReportService reportService;
    private final HumanDogService humanDogService;
    private final HumanCatService humanCatService;
    private final HotkeysShelter keyBoardShelter;
    private final com.pengrad.telegrambot.TelegramBot telegramBot;
    private ReportHandler reportHandler;
    private TelegramUserService telegramUserService;
    private final ContactSharingService contactSharingService;

    public TelegramBotUpdateListener(ReportService reportService,
                                     HumanDogService humanDogService,
                                     HumanCatService humanCatService,
                                     HotkeysShelter keyBoardShelter,
                                     TelegramBot telegramBot,
                                     ReportHandler reportHandler,
                                     ContactSharingService contactSharingService) {
        this.reportService = reportService;
        this.humanDogService = humanDogService;
        this.humanCatService = humanCatService;
        this.keyBoardShelter = keyBoardShelter;
        this.telegramBot = telegramBot;
        this.reportHandler = reportHandler;
        this.contactSharingService = contactSharingService;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null) {
                String nameUser = update.message().chat().firstName();
                String textUpdate = update.message().text();
                Integer messageId = update.message().messageId();
                Long chatId = update.message().chat().id();
                scheduledMethod();
                if (update.equals(telegramUserService.getTelegramUserUpdate(chatId))) {
                    telegramUserService.updateTelegramUser(chatId, update);


                    try {
                        if (update.message() != null && update.message().contact() != null) {
                            contactSharingService.shareContact(update);
                        }

                        if (textUpdate != null) {
                            Constants constants = getEnum(textUpdate);
                            switch (constants) {
                                case START:
                                    // Действия при получении команды START
                                    sendMessage(chatId, nameUser + HI);
                                    keyBoardShelter.chooseMenu(chatId);
                                    break;

                                case CAT:
                                    // Действия при выборе кота
                                    keyBoardShelter.sendMenu(chatId);
                                    sendMessage(chatId, Constants.SET_CAT_ANIMAL.getValue());
                                    if (humanCatService.findByChatId(chatId) == null) {
                                        humanCatService.saveCat(new HumanCat(chatId));
                                    }
                                    break;

                                case DOG:
                                    // Действия при выборе собаки
                                    keyBoardShelter.sendMenu(chatId);
                                    sendMessage(chatId, Constants.SET_DOG_ANIMAL.getValue());
                                    if (humanDogService.findByChatId(chatId) == null) {
                                        humanDogService.saveDog(new HumanDog(chatId));
                                    }
                                    break;

                                case MAIN_MENU:
                                case RETURN_MENU:
                                    // Действия при возврате в главное меню
                                    keyBoardShelter.sendMenu(chatId);
                                    break;

                                case DRIVER_SCHEME:
                                    // Действия при получении схемы проезда
                                    sendMessage(chatId, Constants.SCHEMA_2GIS.getValue());
                                    break;

                                case FOR_SAFETY:
                                    // Действия при получении информации о безопасности
                                    sendMessage(chatId, Constants.SAFETY.getValue());
                                    break;

                                case SHELTER_INFO_MENU:
                                    // Действия при выборе меню информации о приюте
                                    keyBoardShelter.sendMenuInfoShelter(chatId);
                                    break;

                                case ABOUT_ANIMAL_SHELTER:
                                case TIPS_AND_RECOMMENDATIONS:
                                    // Действия при запросе информации о приюте
                                    if (humanCatService.findByChatId(chatId) != null) {
                                        sendMessage(chatId, Constants.INFO_ABOUT_SHELTER_CAT.getValue());
                                    } else if (humanDogService.findByChatId(chatId) != null) {
                                        sendMessage(chatId, Constants.INFO_ABOUT_SHELTER_DOG.getValue());
                                    }
                                    break;

                                case DOCUMENTS:
                                    // Действия при запросе информации о документах
                                    sendMessage(chatId, Constants.INFO_ABOUT_DOCUMENTS.getValue());
                                    break;

                                case GET_ANIMAL_WITH_DEFECTS:
                                    // Действия при запросе информации о животных с дефектами
                                    sendMessage(chatId, Constants.INFO_ABOUT_ANIMAL_WITH_DEFECTS.getValue());
                                    break;

                                case SEND_REPORT:
                                    // Действия при запросе отправки отчета
                                    sendMessage(chatId, Constants.INFO_ABOUT_REPORT.getValue());
                                    sendMessage(chatId, Constants.REPORT_EXAMPLE.getValue());
                                    break;

                                case HOW_GET_ANIMAL:
                                    // Действия при запросе информации о том, как взять питомца из приюта
                                    keyBoardShelter.sendMenuTakeAnimal(chatId);
                                    break;

                                case INFO_ABOUT_BOT_BUTTON:
                                    // Действия при запросе информации о боте
                                    sendMessage(chatId, Constants.INFO_ABOUT_BOT.getValue());
                                    break;

                                case GET_USER_CONTACT:
                                    new ReplyKeyboardMarkup(new KeyboardButton(" ")
                                            .requestContact(true));
                                    // Действия при запросе контакта пользователя
                                    contactSharingService.shareContact(update);
                                    break;

                                case SEND_MESSAGE_VOLUNTEER:
                                    try {
                                        URL url = new URL(Constants.VOLUNTEER_URL.getValue());
                                        // Действия при запросе отправки сообщения волонтеру
                                        sendMessage(chatId, Constants.VOLUNTEER_QUESTION.getValue() + url);
                                    } catch (MalformedURLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    break;

                                case EMPTY:
                                    // Действия при получении пустого сообщения
                                    sendMessage(chatId, Constants.EMPTY_MESSAGE.getValue());
                                    break;

                                case SAY_HI:
                                case SAY_HI2:
                                    sendMessage(chatId, "И тебе привет, " + nameUser + ". Возьми питомца из приюта.");
                                    break;

                                default:
                                    // Действия при получении неизвестной команды
                                    sendReplyMessage(chatId, Constants.UNKNOWN_MESSAGE.getValue(), messageId);
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendMessage(chatId, "Произошла ошибка. Пожалуйста, попробуйте ещё раз или обратитесь в поддержку.");

                    }
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendReplyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
        telegramBot.execute(sendMessage);
    }


    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        telegramBot.execute(message);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledMethod() {
        reportHandler.checkReportDays(update, chatId, calendar);
    }
}