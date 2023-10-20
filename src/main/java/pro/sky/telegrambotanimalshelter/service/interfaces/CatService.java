package pro.sky.telegrambotanimalshelter.service.interfaces;

import pro.sky.telegrambotanimalshelter.models.Cat;


public interface CatService {

    Cat add(Cat cat);

    Cat read(long id);

    Cat update(Cat cat);

    Cat delete(long id);


}
