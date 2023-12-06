package pro.sky.telegrambotanimalshelter.service.interfaces;


import pro.sky.telegrambotanimalshelter.models.Dog;

import java.util.Collection;


public interface DogService {

    Dog getByIdDog(Long id);

    Dog addDog(Dog dog);

    Dog updateDog(Dog dog);

    Collection<Dog> getAllDog();

    void removeByIdDog(Long id);
}