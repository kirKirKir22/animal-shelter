package pro.sky.telegrambotanimalshelter.service.interfaces;


import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.HumanCat;

import java.util.Collection;

@Service
public interface HumanCatService {

    HumanCat getByIdHumanCat(Long id);

    HumanCat addHumanCat(HumanCat personCat);

    HumanCat updateHumanCat(HumanCat personCat);

    Collection<HumanCat> getAllHumanCat();

    void removeByIdHumanCat(Long id);

    HumanCat findByChatId(long chatId);

    HumanCat saveCat(HumanCat humanCat);

    boolean existsByChatId(Long chatId);
}