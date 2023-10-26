package pro.sky.telegrambotanimalshelter.service.interfaces;

import pro.sky.telegrambotanimalshelter.models.Cat;

import java.util.List;


public interface CatService {

    Cat add(Cat cat);

    Cat read(long id);

    Cat update(Cat cat);

    Cat delete(long id);

    List<Cat> findAll();


}
