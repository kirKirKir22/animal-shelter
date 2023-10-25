package pro.sky.telegrambotanimalshelter.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;
import pro.sky.telegrambotanimalshelter.exceptions.CatException;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final Logger logger = LoggerFactory.getLogger(CatServiceImpl.class);

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat add(Cat cat) {
        logger.info("вызван метод add с данными: " + cat);

        Optional<Cat> existingCat = catRepository.findByName(cat.getName());
        if (existingCat.isPresent()) {
            throw new CatException("Такой кот уже есть в базе данных");
        }

        Cat savedCat = catRepository.save(cat);
        logger.info("метод add вернул: " + savedCat);

        return savedCat;
    }

    @Override
    public Cat read(long id) {
        logger.info("был вызван метод read с данными " + id);

        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) {
            throw new CatException("Кот в базе не найден");
        }

        Cat readCat = cat.get();
        logger.info("метод read вернул: " + readCat);
        return readCat;
    }

    @Override
    public Cat update(Cat cat) {
        logger.info("был вызван метод update с данными: " + cat);

        if (!catRepository.existsById(cat.getId())) {
            throw new CatException("Кот в базе не найден");
        }

        Cat updatedCat = catRepository.save(cat);
        logger.info("метод update вернул: " + updatedCat);

        return updatedCat;
    }

    @Override
    public Cat delete(long id) {
        logger.info("был вызван метод delete с данными: " + id);

        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) {
            throw new CatException("Кот в базе не найден");
        }

        Cat deleteCat = cat.get();
        catRepository.deleteById(id);
        logger.info("метод delete вернул: " + deleteCat);
        return deleteCat;
    }

    @Override
    public List<Cat> findAll() {
        logger.info("был вызван метод findAll");

        List<Cat> catList = catRepository.findAll();

        logger.info("метод findAll вернул: " + catList);
        return catList;
    }
}
