package pro.sky.telegrambotanimalshelter.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sky.telegrambotanimalshelter.listener.TelegramBotUpdateListener;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @Mock
    private TelegramBotUpdateListener telegramBot;

    @InjectMocks
    private ReportController reportController;

    @Test
    void testDownloadReport() {
        Long reportId = 1L;
        Report testReport = new Report();
        testReport.setId(reportId);

        when(reportService.getByIdReport(reportId)).thenReturn(testReport);

        Report result = reportController.downloadReport(reportId);

        assertEquals(testReport, result);
    }

    @Test
    void testRemove() {
        Long reportId = 1L;

        reportController.remove(reportId);

        verify(reportService, times(1)).removeByIdReport(reportId);
    }

    @Test
    void testGetAll() {
        Report testReport = new Report();

        when(reportService.getAllReport()).thenReturn(Collections.singletonList(testReport));

        Collection<Report> result = reportController.getAll().getBody();

        assertEquals(1, result.size());
    }

    @Test
    void testDownloadPhotoFromDB() {
        Long reportId = 1L;
        Report testReport = new Report();
        testReport.setId(reportId);
        byte[] testData = {1, 2, 3}; // Sample data for the report image
        testReport.setData(testData);

        when(reportService.getByIdReport(reportId)).thenReturn(testReport);

        ResponseEntity<byte[]> response = reportController.downloadPhotoFromDB(reportId);
        byte[] result = response.getBody();

        assertNotNull(result);
        assertArrayEquals(testData, result);
    }



    @Test
    void testSendMessageToPerson() {
        Long chatId = 123456789L;
        String message = "Test message";

        reportController.sendMessageToPerson(chatId, message);

        verify(telegramBot, times(1)).sendMessage(chatId, message);
    }
}
