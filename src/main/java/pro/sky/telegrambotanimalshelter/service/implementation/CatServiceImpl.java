package pro.sky.telegrambotanimalshelter.service.implementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.CatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.Collection;


@Service
public class CatServiceImpl implements CatService {

    private final CatRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CatServiceImpl.class);

    public CatServiceImpl(CatRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cat getByIdCat(Long id) {
        LOGGER.info("Was invoked method to get a cat by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(CatNotFoundException::new);
    }


    @Override
    public Cat addCat(Cat cat) {
        LOGGER.info("Was invoked method to add a cat");
        return this.repository.save(cat);
    }


    @Override
    public Cat updateCat(Cat cat) {
        LOGGER.info("Was invoked method to update a cat");
        if (cat.getId() != null && getByIdCat(cat.getId()) != null) {
            return this.repository.save(cat);
        }
        throw new CatNotFoundException();
    }


    @Override
    public Collection<Cat> getAllCat() {
        LOGGER.info("Was invoked method to get all cats");
        return this.repository.findAll();
    }


    @Override
    public void removeByIdCat(Long id) {
        LOGGER.info("Was invoked method to remove a cat by id={}", id);
        this.repository.deleteById(id);
    }
}