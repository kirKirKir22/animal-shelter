package pro.sky.telegrambotanimalshelter.service.interfaces;


import pro.sky.telegrambotanimalshelter.models.HumanCat;

import java.util.Collection;


public interface HumanCatService {

    HumanCat getByIdHumanCat(Long id);

    HumanCat addHumanCat(HumanCat personCat);

    HumanCat updateHumanCat(HumanCat personCat);

    Collection<HumanCat> getAllHumanCat();

    void removeByIdHumanCat(Long id);
}