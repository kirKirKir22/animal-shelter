package pro.sky.telegrambotanimalshelter.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.repository.DogRepository;
import pro.sky.telegrambotanimalshelter.exceptions.DogException;
import pro.sky.telegrambotanimalshelter.service.interfaces.DogService;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final Logger logger = LoggerFactory.getLogger(DogServiceImpl.class);
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog add(Dog dog) {
        logger.info("вызван метод add с данными: " + dog);

        Optional<Dog> existingDog = dogRepository.findByName(dog.getName());
        if (existingDog.isPresent()) {
            throw new DogException("Такая собака уже есть в базе данных");
        }

        Dog savedDog = dogRepository.save(dog);
        logger.info("метод add вернул: " + savedDog);

        return savedDog;
    }

    @Override
    public Dog read(long id) {
        logger.info("был вызван метод read с данными " + id);

        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isEmpty()) {
            throw new DogException("Собака в базе не найдена");
        }

        Dog readDog = dog.get();
        logger.info("метод read вернул: " + readDog);
        return readDog;
    }

    @Override
    public Dog update(Dog dog) {
        logger.info("был вызван метод update с данными: " + dog);

        if (!dogRepository.existsById(dog.getId())) {
            throw new DogException("Собака в базе не найдена");
        }

        Dog updatedDog = dogRepository.save(dog);
        logger.info("метод update вернул: " + updatedDog);

        return updatedDog;
    }

    @Override
    public Dog delete(long id) {
        logger.info("был вызван метод delete с данными: " + id);

        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isEmpty()) {
            throw new DogException("Собака в базе не найдена");
        }

        Dog deleteDog = dog.get();
        dogRepository.deleteById(id);
        logger.info("метод delete вернул: " + deleteDog);
        return deleteDog;
    }

    @Override
    public List<Dog> findAll() {
        logger.info("был вызван метод findAll");

        List<Dog> dogList = dogRepository.findAll();

        logger.info("метод findAll вернул: " + dogList);
        return dogList;
    }
}