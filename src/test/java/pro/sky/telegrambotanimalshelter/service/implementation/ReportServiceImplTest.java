package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ReportServiceImplTest {

    @Mock
    private ReportRepository repository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdReport() {
        Long reportId = 1L;
        Report testReport = new Report();
        testReport.setId(reportId);

        when(repository.findById(reportId)).thenReturn(Optional.of(testReport));

        Report result = reportService.getByIdReport(reportId);

        assertEquals(testReport, result);
    }

    @Test
    void testAddReport() {
        Report testReport = new Report();

        when(repository.save(testReport)).thenReturn(testReport);

        Report result = reportService.addReport(testReport);

        assertEquals(testReport, result);

        verify(repository, times(1)).save(testReport);
    }

    @Test
    void testUpdateReport() {
        Long reportId = 1L;
        Report testReport = new Report();
        testReport.setId(reportId);

        when(repository.findById(reportId)).thenReturn(Optional.of(testReport));
        when(repository.save(testReport)).thenReturn(testReport);

        Report result = reportService.updateReport(testReport);

        assertEquals(testReport, result);

        verify(repository, times(1)).save(testReport);
    }

    @Test
    void testGetAllReport() {
        Report testReport = new Report();

        when(repository.findAll()).thenReturn(Collections.singletonList(testReport));

        List<Report> result = reportService.getAllReport();

        assertEquals(1, result.size());
        assertTrue(result.contains(testReport));
    }

    @Test
    void testRemoveByIdReport() {
        Long reportId = 1L;

        reportService.removeByIdReport(reportId);

        verify(repository, times(1)).deleteById(reportId);
    }

    @Test
    void testFindByChatId() {
        long chatId = 123456789L;
        Report testReport = new Report();

        when(repository.findByChatId(chatId)).thenReturn(testReport);

        Report result = reportService.findByChatId(chatId);

        assertEquals(testReport, result);
    }

    @Test
    void testSave() {
        Report testReport = new Report();

        reportService.save(testReport);

        verify(repository, times(1)).save(testReport);
    }

    @Test
    void testFindAll() {
        Report testReport = new Report();

        when(repository.findAll()).thenReturn(Collections.singletonList(testReport));

        List<Report> result = reportService.findAll();

        assertEquals(1, result.size());
        assertTrue(result.contains(testReport));
    }

    @Test
    void testFindAllByLastMessageMsLessThan() {
        long time = 100L;
        Report testReport = new Report();

        when(repository.findAllByLastMessageMsLessThan(time)).thenReturn(Collections.singletonList(testReport));

        List<Report> result = reportService.findAllByLastMessageMsLessThan(time);

        assertEquals(1, result.size());
        assertTrue(result.contains(testReport));
    }
}
