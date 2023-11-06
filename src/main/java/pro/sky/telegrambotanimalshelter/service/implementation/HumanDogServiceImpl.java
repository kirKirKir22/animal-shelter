package pro.sky.telegrambotanimalshelter.service.implementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.exceptions.HumanDogNotFoundException;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.repository.HumanDogRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanDogService;

import java.util.Collection;
import java.util.List;


@Service
public class HumanDogServiceImpl implements HumanDogService {

    private final HumanDogRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(HumanDogServiceImpl.class);

    public HumanDogServiceImpl(HumanDogRepository repository) {
        this.repository = repository;
    }


    @Override
    public HumanDog getByIdHumanDog(Long id) {
        logger.info("Was invoked method to get a personDog by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(HumanDogNotFoundException::new);
    }


    @Override
    public HumanDog addHumanDog(HumanDog personDog) {
        logger.info("Was invoked method to add a personDog");
        return this.repository.save(personDog);
    }


    @Override
    public HumanDog updateHumanDog(HumanDog humanDog) {
        logger.info("Was invoked method to update a personDog");
        if (humanDog.getId() != null) {
            if (getByIdHumanDog(humanDog.getId()) != null) {
                return this.repository.save(humanDog);
            }
        }
        throw new HumanDogNotFoundException();
    }


    @Override
    public Collection<HumanDog> getAllHumanDog() {
        logger.info("Was invoked method to get all personsDod");
        return this.repository.findAll();
    }

    @Override
    public void removeByIdHumanDog(Long id) {
        logger.info("Was invoked method to remove a personDog by id={}", id);
        this.repository.deleteById(id);
    }

    @Override
    public HumanDog findByChatId(long chatId) {
        return repository.findByChatId(chatId);
    }

    @Override
    public HumanDog saveDog(HumanDog humanDog) {
        return repository.save(humanDog);
    }

    @Override
    public List<HumanDog> findAll() {
        return repository.findAll();
    }

}