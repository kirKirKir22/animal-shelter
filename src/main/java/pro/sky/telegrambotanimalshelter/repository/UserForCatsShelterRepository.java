package pro.sky.telegrambotanimalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotanimalshelter.models.UserForCatsShelter;

@Repository
public interface UserForCatsShelterRepository extends JpaRepository<UserForCatsShelter, Long> {
}
