package pro.sky.telegrambotanimalshelter.service.interfaces;


import pro.sky.telegrambotanimalshelter.models.Report;

public interface ReportService {

    Report add(Report report);

    Report read(long id);

    Report update(Report report);

    Report delete(long id);


}
