package pro.sky.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.listener.TelegramBotUpdateListener;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.service.implementation.ReportServiceImpl;

import java.util.Collection;


@RestController
@RequestMapping("photoReports")
public class ReportController {

    // Сервис для работы с отчетами
    private final ReportServiceImpl reportService;

    // Сервис для взаимодействия с Telegram Bot
    private TelegramBotUpdateListener telegramBot;

    // Тип файла, используется для установки заголовков HTTP-ответа при отправке фотографий
    private final String fileType = "image/jpeg";

    // Конструктор контроллера для инъекции сервиса работы с отчетами
    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    // Конструктор контроллера для инъекции сервисов работы с отчетами и Telegram Bot
    @Autowired
    public ReportController(ReportServiceImpl reportService, TelegramBotUpdateListener telegramBot) {
        this.reportService = reportService;
        this.telegramBot = telegramBot;
    }

    // Просмотр отчета по его id
    @Operation(summary = "Просмотр отчетов по id")
    @GetMapping("/{id}/report")
    public Report downloadReport(@Parameter(description = "report id") @PathVariable Long id) {
        return this.reportService.getByIdReport(id);
    }

    // Удаление отчета по его id
    @Operation(summary = "Удаление отчетов по id")
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "report id") @PathVariable Long id) {
        this.reportService.removeByIdReport(id);
    }

    // Просмотр всех отчетов
    @Operation(summary = "Просмотр всех отчетов")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<Report>> getAll() {
        return ResponseEntity.ok(this.reportService.getAllReport());
    }

    // Просмотр фотографии из отчета по его id
    @Operation(summary = "Просмотр фото по id отчета")
    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@Parameter(description = "report id") @PathVariable Long id) {
        Report reportData = this.reportService.getByIdReport(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }

    // Отправка сообщения пользователю
    @Operation(summary = "Отправить сообщение пользователю")
    @GetMapping("/message-to-person")
    public void sendMessageToPerson(
            @Parameter(description = "id чата с пользователем", example = "3984892310")
            @RequestParam Long chat_Id,
            @Parameter(description = "Ваше сообщение")
            @RequestParam String message) {
        telegramBot.sendMessage(chat_Id, message);
    }
}
