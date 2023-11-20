package pro.sky.telegrambotanimalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotanimalshelter.models.Cat;


@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

}