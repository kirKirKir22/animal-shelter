package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.ReportNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportServiceImplTest {

    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reportService = new ReportServiceImpl(repository);
    }

    @Test
    public void testUploadReport_WithValidData_SavesReport() {
        Long personId = 1L;
        byte[] pictureFile = new byte[]{1, 2, 3};
        String filePath = "path/to/file";
        Date dateSendMessage = new Date();
        Long timeDate = System.currentTimeMillis();
        long daysOfReports = 7;

        // Создаем заглушку для объекта File
        com.pengrad.telegrambot.model.File file = mock(com.pengrad.telegrambot.model.File.class);
        when(file.fileSize()).thenReturn(12345L);

        reportService.uploadReport(personId, pictureFile, file, null, null, null, null, daysOfReports);

        verify(repository, times(1)).save(any(Report.class));
    }

    @Test
    public void testGetByIdReport_WithValidId_ReturnsReport() {
        Report report = new Report();
        report.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(report));

        Report result = reportService.getByIdReport(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetByIdReport_WithInvalidId_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.getByIdReport(1L));
    }

    @Test
    public void testAddReport_AddsReport() {
        Report report = new Report();
        when(repository.save(any(Report.class))).thenReturn(report);

        Report result = reportService.addReport(report);

        assertNotNull(result);
        assertEquals(report, result);
    }

    @Test
    public void testUpdateReport_WithValidId_UpdatesReport() {
        Report report = new Report();
        report.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(report));
        when(repository.save(any(Report.class))).thenReturn(report);

        Report result = reportService.updateReport(report);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateReport_WithInvalidId_ThrowsException() {
        Report report = new Report();
        report.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.updateReport(report));
    }

    @Test
    public void testRemoveByIdReport_RemovesReport() {
        reportService.removeByIdReport(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByChatId_WithExistingChatId_ReturnsReport() {
        Report report = new Report();
        long chatId = 123L;
        when(repository.findByChatId(chatId)).thenReturn(report);

        Report result = reportService.findByChatId(chatId);

        assertNotNull(result);
        assertEquals(report, result);
    }

    @Test
    public void testFindByChatId_WithNonExistingChatId_ReturnsNull() {
        long chatId = 123L;
        when(repository.findByChatId(chatId)).thenReturn(null);

        Report result = reportService.findByChatId(chatId);

        assertNull(result);
    }
}