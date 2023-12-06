package pro.sky.telegrambotanimalshelter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.listener.TelegramBotUpdateListener;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @MockBean
    private TelegramBotUpdateListener telegramBotUpdateListener;

    @Test
    public void testDownloadReport() throws Exception {
        // Подготовка данных для теста
        Report mockReport = new Report(); // Замените на фактический объект для тестирования
        Long id = 1L;

        // Mocking behavior
        given(reportService.getByIdReport(id)).willReturn(mockReport);

        // Выполнение запроса
        mockMvc.perform(get("/photoReports/{id}/report", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemove() throws Exception {
        // Подготовка данных для теста
        Long id = 1L;

        // Выполнение запроса
        mockMvc.perform(delete("/photoReports/{id}", id))
                .andExpect(status().isOk());

        // Проверка, что сервис вызывал метод removeByIdReport с указанным id
        verify(reportService, times(1)).removeByIdReport(id);
    }

    @Test
    public void testGetAll() throws Exception {
        // Подготовка данных для теста
        Report mockReport1 = new Report(); // Замените на фактические объекты для тестирования
        Report mockReport2 = new Report();
        List<Report> reports = Arrays.asList(mockReport1, mockReport2);

        // Mocking behavior
        given(reportService.getAllReport()).willReturn(reports);

        // Выполнение запроса
        mockMvc.perform(get("/photoReports/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))); // Замените на фактическую проверку значений
    }

    @Test
    public void testDownloadPhotoFromDB() throws Exception {
        // Подготовка данных для теста
        Report mockReport = new Report(); // Замените на фактический объект для тестирования
        mockReport.setData("test image data".getBytes()); // Установите данные

        Long id = 1L;

        // Mocking behavior
        given(reportService.getByIdReport(id)).willReturn(mockReport);

        // Выполнение запроса
        mockMvc.perform(get("/photoReports/{id}/photo-from-db", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

    @Test
    public void testSendMessageToPerson() throws Exception {
        // Подготовка данных для теста
        Long chatId = 12345L;
        String message = "Test message";

        // Выполнение запроса
        mockMvc.perform(get("/photoReports/message-to-person")
                        .param("chatId", String.valueOf(chatId))
                        .param("message", message))
                .andExpect(status().isOk());

        // Проверка, что метод sendMessage был вызван у объекта telegramBotUpdateListener
        verify(telegramBotUpdateListener, times(1)).sendMessage(chatId, message);
    }

}