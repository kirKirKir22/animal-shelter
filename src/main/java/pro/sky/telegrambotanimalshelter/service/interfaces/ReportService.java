package pro.sky.telegrambotanimalshelter.service.interfaces;

import com.pengrad.telegrambot.model.File;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.Report;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public interface ReportService {

    void uploadReport(Long personId, byte[] pictureFile, File file, String ration, String health,
                      String habits, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports);

    void uploadReport(Long personId, byte[] pictureFile, File file,
                      String caption, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports);

    Report getByIdReport(Long id);

    Collection<Report> getAllReport();

    Report addReport(Report report);

    Report updateReport(Report report);

    void removeByIdReport(Long id);


    Report findByChatId(long chatId);

    void save(Report report);

    List<Report> findAll();
}