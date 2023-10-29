package pro.sky.telegrambotanimalshelter.service.interfaces;



import pro.sky.telegrambotanimalshelter.models.Cat;

import java.util.Collection;


public interface CatService {
    Cat getByIdCat(Long id);

    Cat addCat(Cat cat);

    Cat updateCat(Cat cat);

    Collection<Cat> getAllCat();

    void removeByIdCat(Long id);
}
