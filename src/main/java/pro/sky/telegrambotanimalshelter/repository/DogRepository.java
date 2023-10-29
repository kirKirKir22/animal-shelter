package pro.sky.telegrambotanimalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambotanimalshelter.models.Dog;


@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
