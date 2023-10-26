package pro.sky.telegrambotanimalshelter.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.ReportNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report add(Report report) {
        logger.info("вызван метод add с данными: " + report);

        Optional<Report> existingReport = reportRepository.findById(report.getId());
        if (existingReport.isPresent()) {
            throw new ReportNotFoundException("Такой отчет уже есть в базе данных");
        }

        Report savedReport = reportRepository.save(report);
        logger.info("метод add вернул: " + savedReport);

        return savedReport;
    }

    @Override
    public Report read(long id) {
        logger.info("был вызван метод read с данными " + id);

        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new ReportNotFoundException("Отчет в базе не найден");
        }

        Report readReport = report.get();
        logger.info("метод read вернул: " + readReport);
        return readReport;
    }

    @Override
    public Report update(Report report) {
        logger.info("был вызван метод update с данными: " + report);

        if (!reportRepository.existsById(report.getId())) {
            throw new ReportNotFoundException("Отчет в базе не найден");
        }

        Report updatedReport = reportRepository.save(report);
        logger.info("метод update вернул: " + updatedReport);

        return updatedReport;
    }

    @Override
    public Report delete(long id) {
        logger.info("был вызван метод delete с данными: " + id);

        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new ReportNotFoundException("Отчет в базе не найден");
        }

        Report deleteReport = report.get();
        reportRepository.deleteById(id);
        logger.info("метод delete вернул: " + deleteReport);
        return deleteReport;
    }

    @Override
    public List<Report> findAll() {
        logger.info("был вызван метод findAll");

        List<Report> reportList = reportRepository.findAll();

        logger.info("метод findAll вернул: " + reportList);
        return reportList;
    }
}





