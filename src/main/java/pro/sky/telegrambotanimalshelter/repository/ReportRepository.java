package pro.sky.telegrambotanimalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotanimalshelter.models.Report;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByChatId(Long chatId);
}