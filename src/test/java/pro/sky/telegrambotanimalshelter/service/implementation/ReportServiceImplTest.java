package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.telegrambotanimalshelter.exceptions.ReportNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepository;

    @Test
    public void addReport_NonExistingReport_SaveAndReturnReport() {
        Report report = new Report();
        report.setId(1L);

        Mockito.when(reportRepository.findById(report.getId())).thenReturn(Optional.empty());
        Mockito.when(reportRepository.save(report)).thenReturn(report);

        Report addedReport = reportService.add(report);
        assertNotNull(addedReport);
        assertEquals(report, addedReport);
    }

    @Test
    public void addReport_ExistingReport_ThrowException() {
        Report report = new Report();
        report.setId(1L);

        Mockito.when(reportRepository.findById(report.getId())).thenReturn(Optional.of(report));

        assertThrows(ReportNotFoundException.class, () -> reportService.add(report));
    }

    @Test
    public void readReport_ExistingReport_ReturnReport() {
        long id = 1;
        Report report = new Report();
        report.setId(id);

        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.of(report));

        Report readReport = reportService.read(id);
        assertNotNull(readReport);
        assertEquals(report, readReport);
    }

    @Test
    public void readReport_NonExistingReport_ThrowException() {
        long id = 1;

        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.read(id));
    }

    @Test
    public void updateReport_ExistingReport_UpdateAndReturnReport() {
        Report report = new Report();
        report.setId(1L);

        Mockito.when(reportRepository.existsById(report.getId())).thenReturn(true);
        Mockito.when(reportRepository.save(report)).thenReturn(report);

        Report updatedReport = reportService.update(report);
        assertNotNull(updatedReport);
        assertEquals(report, updatedReport);
    }

    @Test
    public void updateReport_NonExistingReport_ThrowException() {
        Report report = new Report();
        report.setId(1L);

        Mockito.when(reportRepository.existsById(report.getId())).thenReturn(false);

        assertThrows(ReportNotFoundException.class, () -> reportService.update(report));
    }

    @Test
    public void deleteReport_ExistingReport_DeleteAndReturnReport() {
        long id = 1;
        Report report = new Report();
        report.setId(id);

        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.of(report));
        Mockito.when(reportRepository.save(report)).thenReturn(report);

        Report deletedReport = reportService.delete(id);
        assertNotNull(deletedReport);
        assertEquals(report, deletedReport);
    }

    @Test
    public void deleteReport_NonExistingReport_ThrowException() {
        long id = 1;

        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.delete(id));
    }

    @Test
    public void findAllReports_ExistingReports_ReturnListOfReports() {
        List<Report> reportList = new ArrayList<>();
        reportList.add(new Report());
        reportList.add(new Report());

        Mockito.when(reportRepository.findAll()).thenReturn(reportList);

        List<Report> foundReports = reportService.findAll();
        assertNotNull(foundReports);
        assertEquals(reportList, foundReports);
    }
}
