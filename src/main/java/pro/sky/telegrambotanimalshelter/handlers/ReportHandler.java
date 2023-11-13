package pro.sky.telegrambotanimalshelter.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.constants.Constants;
import pro.sky.telegrambotanimalshelter.listener.TelegramBotUpdateListener;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.sky.telegrambotanimalshelter.constants.Constants.UPLOAD_PHOTO_ERROR;

@Service
public class ReportHandler {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final ReportService reportService;
    private com.pengrad.telegrambot.TelegramBot telegramBot;
    private final Pattern pattern = Pattern.compile(Constants.REGEX_MESSAGE.getValue());

    public ReportHandler(ReportService reportService, TelegramBot telegramBot) {
        this.reportService = reportService;
        this.telegramBot = telegramBot;
    }

    // Метод для проверки количества дней между отправкой отчетов
    public void checkReportDays(Update update, long chatId, Calendar calendar) {
        long compareTime = calendar.get(Calendar.DAY_OF_MONTH);
        if (reportService.findByChatId(chatId) == null) {
            reportService.save(new Report(chatId));
        }
        reportService.findByChatId(chatId).setDays(reportService.findAll().stream()
                .filter(s -> Objects.equals(s.getChatId(), chatId))
                .count() + 1);

        Long lastMessageTime = reportService.findByChatId(chatId).getLastMessageMs();
        if (lastMessageTime != null) {
            Date lastDateSendMessage = new Date(lastMessageTime * 1000);
            long numberOfDay = lastDateSendMessage.getDate();

            if (reportService.findByChatId(chatId).getDays() < 30) {
                if (compareTime != numberOfDay) {
                    if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
                        getReport(update);
                        checkResults();
                    }
                } else {
                    if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
                        sendMessage(chatId, Constants.ALREADY_SEND_REPORT.getValue());
                    }
                }
                if (reportService.findByChatId(chatId).getDays() >= 30) {
                    sendMessage(chatId, Constants.TRIAL_PERIOD_PASSED.getValue());
                }
            }
        } else {
            if (update.message() != null && update.message().photo() != null && update.message().caption() != null) {
                getReport(update);
            }
        }
        if (update.message() != null && update.message().photo() != null && update.message().caption() == null) {
            sendMessage(chatId, Constants.INCORRECT_REPORT.getValue());
        }
    }

    // Метод для обработки и сохранения отчета
    public void getReport(Update update) {
        Matcher matcher = pattern.matcher(update.message().caption());
        Long reportDays = reportService.findByChatId(update.message().chat().id()).getDays();
        if (matcher.matches()) {
            String ration = matcher.group(3);
            String health = matcher.group(7);
            String habits = matcher.group(11);

            GetFile getFileRequest = new GetFile(update.message().photo()[1].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            try {
                File file = getFileResponse.file();
                String fullPathPhoto = file.filePath();

                long timeDate = update.message().date();
                Date dateSendMessage = new Date(timeDate * 1000);
                byte[] fileContent = telegramBot.getFileContent(file);
                reportService.uploadReport(update.message().chat().id(), fileContent, file,
                        ration, health, habits, fullPathPhoto, dateSendMessage, timeDate, reportDays);

                telegramBot.execute(new SendMessage(update.message().chat().id(), Constants.REPORT_IS_OK.getValue()));

                logger.info(Constants.REPORT_RECEIVED.getValue() + update.message().chat().id());
            } catch (IOException e) {
                System.out.println(UPLOAD_PHOTO_ERROR);
            }
        } else {
            GetFile getFileRequest = new GetFile(update.message().photo()[1].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            try {
                File file = getFileResponse.file();
                String fullPathPhoto = file.filePath();

                long timeDate = update.message().date();
                Date dateSendMessage = new Date(timeDate * 1000);
                byte[] fileContent = telegramBot.getFileContent(file);
                reportService.uploadReport(update.message().chat().id(), fileContent, file, update.message().caption(),
                        fullPathPhoto, dateSendMessage, timeDate, reportDays);

                telegramBot.execute(new SendMessage(update.message().chat().id(), Constants.REPORT_IS_OK.getValue()));
                logger.info(Constants.REPORT_RECEIVED.getValue() + update.message().chat().id());
            } catch (IOException e) {
                System.out.println(UPLOAD_PHOTO_ERROR);
            }
        }
    }

    // Метод для проверки и отправки уведомлений
    private void checkResults() {
        var twoDay = 172800000;
        var nowTime = new Date().getTime() - twoDay;
        var getDistinct = this.reportService.findAll().stream()
                .sorted(Comparator
                        .comparing(Report::getChatId))
                .max(Comparator
                        .comparing(Report::getLastMessageMs));
        getDistinct.stream()
                .filter(i -> i.getLastMessageMs() * 1000 < nowTime)
                .forEach(s -> sendMessage(s.getChatId(), Constants.REPORT_NOTIFICATION.getValue()));
    }

    // Метод для отправки сообщений через Telegram бот
    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        telegramBot.execute(message);
    }
}