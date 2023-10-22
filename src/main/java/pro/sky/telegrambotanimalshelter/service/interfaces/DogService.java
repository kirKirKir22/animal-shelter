package pro.sky.telegrambotanimalshelter.service.interfaces;

import pro.sky.telegrambotanimalshelter.models.Dog;

public interface DogService {

    Dog add(Dog dog);

    Dog read(long id);

    Dog update(Dog dog);

    Dog delete(long id);


}
