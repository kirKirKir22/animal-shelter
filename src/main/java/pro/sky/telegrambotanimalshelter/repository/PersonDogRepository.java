package pro.sky.telegrambotanimalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotanimalshelter.models.PersonDog;

import javax.transaction.Transactional;


@Repository
public interface PersonDogRepository extends JpaRepository<PersonDog, Long> {

    PersonDog findByChatId(Long chatId);

    @Transactional
    @Modifying
    @Query("UPDATE PersonDog p set p.name = :name, p.phone = :phone where p.chatId = :chatId")
    public void updatePersonDog(@Param("name") String name,
                                @Param("phone") String phone,
                                @Param("chatId") Long chatId);
}
