package pro.sky.telegrambotanimalshelter.service.implementation;

import com.pengrad.telegrambot.model.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.ReportNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;


import java.util.Collection;
import java.util.Date;


@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }


    @Override
    public void uploadReport(Long personId, byte[] pictureFile, File file, String ration, String health, String habits,
                             String filePath, Date dateSendMessage, Long timeDate, long daysOfReports) {
        logger.info("Was invoked method to uploadReportData");
        Report report = new Report();

        report.setLastMessage(dateSendMessage);
        report.setDays(daysOfReports);
        report.setFilePath(filePath);
        report.setFileSize(file.fileSize());
        report.setLastMessageMs(timeDate);
        report.setChatId(personId);
        report.setData(pictureFile);
        report.setRation(ration);
        report.setHealth(health);
        report.setHabits(habits);
        this.repository.save(report);
    }


    public void uploadReport(Long personId, byte[] pictureFile, File file,
                             String caption, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports)  {
        logger.info("Was invoked method to uploadReportData");

        Report report = new Report();
        report.setLastMessage(dateSendMessage);
        report.setDays(daysOfReports);
        report.setFilePath(filePath);
        report.setChatId(personId);
        report.setFileSize(file.fileSize());
        report.setData(pictureFile);
        report.setCaption(caption);
        report.setLastMessageMs(timeDate);
        this.repository.save(report);
    }


    @Override
    public Report getByIdReport(Long id) {
        logger.info("Was invoked method to get a report by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }


    @Override
    public Collection<Report> getAllReport() {
        logger.info("Was invoked method to get all reports");
        return this.repository.findAll();
    }


    @Override
    public Report addReport(Report report) {
        logger.info("Was invoked method to add a report");
        return this.repository.save(report);
    }

    @Override
    public Report updateReport(Report report) {
        logger.info("Was invoked method to update a report");
        if (report.getId() != null) {
            if (getByIdReport(report.getId()) != null) {
                return this.repository.save(report);
            }
        }
        throw new ReportNotFoundException();
    }


    @Override
    public void removeByIdReport(Long id) {
        logger.info("Was invoked method to remove a report by id={}", id);
        this.repository.deleteById(id);
    }
}