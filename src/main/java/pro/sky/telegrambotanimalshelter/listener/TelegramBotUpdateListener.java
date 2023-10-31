package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.telegrambotanimalshelter.exceptions.MenuDoesNotWorkException;
import pro.sky.telegrambotanimalshelter.handlers.ReportHandler;
import pro.sky.telegrambotanimalshelter.keyboard.HotkeysShelter;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.repository.HumanCatRepository;
import pro.sky.telegrambotanimalshelter.repository.HumanDogRepository;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import static pro.sky.telegrambotanimalshelter.constants.Constants.*;

@Component
public class TelegramBotUpdateListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final ReportRepository reportRepository;
    private final HumanDogRepository humanDogRepository;
    private final HumanCatRepository humanCatRepository;
    private final HotkeysShelter keyBoardShelter;
    private final ReportService reportService;
    private final com.pengrad.telegrambot.TelegramBot telegramBot;
    private ReportHandler reportHandler;

    public TelegramBotUpdateListener(ReportRepository reportRepository, HumanDogRepository humanDogRepository,
                                     HumanCatRepository humanCatRepository, HotkeysShelter keyBoardShelter,
                                     ReportService reportService, TelegramBot telegramBot) {
        this.reportRepository = reportRepository;
        this.humanDogRepository = humanDogRepository;
        this.humanCatRepository = humanCatRepository;
        this.keyBoardShelter = keyBoardShelter;
        this.reportService = reportService;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.stream()
                .filter(Objects::nonNull)
                .toList()
                .forEach(update -> {
                    logger.info("Processing update: {}", update);
                    if (update.message() != null) {
                        String nameUser = update.message().chat().firstName();
                        String textUpdate = update.message().text();
                        Integer messageId = update.message().messageId();
                        Long chatId = update.message().chat().id();
                        Calendar calendar = new GregorianCalendar();

                        reportHandler = new ReportHandler(reportRepository, reportService, telegramBot);
                        reportHandler.checkReportDays(update, chatId, calendar);

                        try {
                            if (update.message() != null && update.message().contact() != null) {
                                shareContact(update);
                            }

                            if (textUpdate != null) {
                                switch (textUpdate) {
                                    case START:
                                        // Действия при получении команды START
                                        sendMessage(chatId, nameUser + HI);
                                        keyBoardShelter.chooseMenu(chatId);
                                        break;

                                    case CAT:
                                        // Действия при выборе кота
                                        keyBoardShelter.sendMenu(chatId);
                                        sendMessage(chatId, SET_CAT_ANIMAL);
                                        if (humanCatRepository.findByChatId(chatId) == null) {
                                            humanCatRepository.save(new HumanCat(chatId));
                                        }
                                        break;

                                    case DOG:
                                        // Действия при выборе собаки
                                        keyBoardShelter.sendMenu(chatId);
                                        sendMessage(chatId, SET_DOG_ANIMAL);
                                        if (humanDogRepository.findByChatId(chatId) == null) {
                                            humanDogRepository.save(new HumanDog(chatId));
                                        }
                                        break;

                                    case MAIN_MENU:
                                    case RETURN_MENU:
                                        // Действия при возврате в главное меню
                                        keyBoardShelter.sendMenu(chatId);
                                        break;

                                    case DRIVER_SCHEME:
                                        // Действия при получении схемы проезда
                                        sendMessage(chatId, SCHEMA_2GIS);
                                        break;

                                    case FOR_SAFETY:
                                        // Действия при получении информации о безопасности
                                        sendMessage(chatId, SAFETY);
                                        break;

                                    case SHELTER_INFO_MENU:
                                        // Действия при выборе меню информации о приюте
                                        keyBoardShelter.sendMenuInfoShelter(chatId);
                                        break;

                                    case ABOUT_ANIMAL_SHELTER:
                                    case TIPS_AND_RECOMMENDATIONS:
                                        // Действия при запросе информации о приюте
                                        if (humanCatRepository.findByChatId(chatId) != null) {
                                            sendMessage(chatId, INFO_ABOUT_SHELTER_CAT);
                                        } else if (humanDogRepository.findByChatId(chatId) != null) {
                                            sendMessage(chatId, INFO_ABOUT_SHELTER_DOG);
                                        }
                                        break;

                                    case DOCUMENTS:
                                        // Действия при запросе информации о документах
                                        sendMessage(chatId, INFO_ABOUT_DOCUMENTS);
                                        break;

                                    case GET_ANIMAL_WITH_DEFECTS:
                                        // Действия при запросе информации о животных с дефектами
                                        sendMessage(chatId, INFO_ABOUT_ANIMAL_WITH_DEFECTS);
                                        break;

                                    case SEND_REPORT:
                                        // Действия при запросе отправки отчета
                                        sendMessage(chatId, INFO_ABOUT_REPORT);
                                        sendMessage(chatId, REPORT_EXAMPLE);
                                        break;

                                    case HOW_GET_ANIMAL:
                                        // Действия при запросе информации о том, как взять питомца из приюта
                                        keyBoardShelter.sendMenuTakeAnimal(chatId);
                                        break;

                                    case INFO_ABOUT_BOT_BUTTON:
                                        // Действия при запросе информации о боте
                                        sendMessage(chatId, INFO_ABOUT_BOT);
                                        break;

                                    case GET_USER_CONTACT:
                                        new ReplyKeyboardMarkup(new KeyboardButton(" ")
                                                .requestContact(true));
                                        // Действия при запросе контакта пользователя
                                        shareContact(update);
                                        break;

                                    case SEND_MESSAGE_VOLUNTEER:
                                        try {
                                            URL url = new URL(VOLUNTEER_URL);
                                            // Действия при запросе отправки сообщения волонтеру
                                            sendMessage(chatId, VOLUNTEER_QUESTION + url);
                                        } catch (MalformedURLException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;

                                    case EMPTY:
                                        // Действия при получении пустого сообщения
                                        sendMessage(chatId, EMPTY_MESSAGE);
                                        break;

                                    case SAY_HI:
                                    case SAY_HI2:
                                        sendMessage(chatId, "И тебе привет, " + nameUser + ". Возьми питомца из приюта.");
                                        break;

                                    default:
                                        // Действия при получении неизвестной команды
                                        sendReplyMessage(chatId, UNKNOWN_MESSAGE, messageId);
                                        break;
                                }
                            }
                        } catch (MenuDoesNotWorkException e) {
                            e.printStackTrace();
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

    public void sendForwardMessage(Long chatId, Integer messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(telegramChatVolunteers, chatId, messageId);
        telegramBot.execute(forwardMessage);
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        telegramBot.execute(message);
    }

    public void shareContact(Update update) {
        if (update.message().contact() != null) {
            String firstName = update.message().contact().firstName();
            String lastName = update.message().contact().lastName();
            String phone = update.message().contact().phoneNumber();
            String username = update.message().chat().username();
            Long finalChatId = update.message().chat().id();
            var sortChatIdDog = humanDogRepository.findAll().stream()
                    .filter(i -> Objects.equals(i.getChatId(), finalChatId)).toList();
            var sortChatIdCat = humanCatRepository.findAll().stream()
                    .filter(i -> Objects.equals(i.getChatId(), finalChatId)).toList();
            if (!sortChatIdDog.isEmpty() || !sortChatIdCat.isEmpty()) {
                sendMessage(finalChatId, ALREADY_IN_DB);
                return;
            }
            if (lastName != null) {
                String name = firstName + " " + lastName + " " + username;
                if (humanCatRepository.findByChatId(finalChatId) == null) {
                    humanCatRepository.save(new HumanCat(name, phone, finalChatId));
                } else {
                    humanCatRepository.updateHumanCat(name, phone, finalChatId);
                }
                if (humanDogRepository.findByChatId(finalChatId) == null) {
                    humanDogRepository.save(new HumanDog(name, phone, finalChatId));
                } else {
                    humanDogRepository.updateHumanDog(name, phone, finalChatId);
                }
                sendMessage(finalChatId, ADD_TO_DB);
                return;
            }
            sendMessage(telegramChatVolunteers, phone + " " + firstName + USER_ADDED_PHONE_NUMBER_TO_DB);
            sendForwardMessage(finalChatId, update.message().messageId());
        }
    }
}