package pro.sky.telegrambotanimalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotanimalshelter.models.HumanCat;

import javax.transaction.Transactional;


@Repository
public interface HumanCatRepository extends JpaRepository<HumanCat, Long> {

    HumanCat findByChatId(Long chatId);

    @Transactional
    @Modifying
    @Query("UPDATE HumanCat p set p.name = :name, p.phone = :phone where p.chatId = :chatId")
    public void updatePersonCat(@Param("name") String name,
                                @Param("phone") String phone,
                                @Param("chatId") Long chatId);
}
