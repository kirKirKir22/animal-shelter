package pro.sky.telegrambotanimalshelter.service.implementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.HumanCatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.repository.HumanCatRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanCatService;

import java.util.Collection;


@Service
public class HumanCatServiceImpl implements HumanCatService {

    private final HumanCatRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(HumanCatServiceImpl.class);

    public HumanCatServiceImpl(HumanCatRepository personCatRepository) {
        this.repository = personCatRepository;
    }


    @Override
    public HumanCat getByIdHumanCat(Long id) {
        logger.info("Was invoked method to get a personCat by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(HumanCatNotFoundException::new);
    }


    @Override
    public HumanCat addHumanCat(HumanCat personCat) {
        logger.info("Was invoked method to add a personCat");
        return this.repository.save(personCat);
    }


    @Override
    public HumanCat updateHumanCat(HumanCat personCat) {
        logger.info("Was invoked method to update a personCat");
        if (personCat.getId() != null) {
            if (getByIdHumanCat(personCat.getId()) != null) {
                return repository.save(personCat);
            }
        }
        throw new HumanCatNotFoundException();
    }


    @Override
    public Collection<HumanCat> getAllHumanCat() {
        logger.info("Was invoked method to get all personsCat");
        return this.repository.findAll();
    }


    @Override
    public void removeByIdHumanCat(Long id) {
        logger.info("Was invoked method to remove a personCat by id={}", id);
        this.repository.deleteById(id);
    }
}