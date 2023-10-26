package pro.sky.telegrambotanimalshelter.service.interfaces;

import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.models.UserForDogsShelter;

import java.util.List;

public interface UserForDogsShelterService {

    UserForDogsShelter add(UserForDogsShelter userForDogsShelter);

    UserForDogsShelter read(long id);

    UserForDogsShelter update(UserForDogsShelter userForDogsShelter);

    UserForDogsShelter delete(long id);

    List<UserForDogsShelter> findAll();

}
