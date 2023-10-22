package pro.sky.telegrambotanimalshelter.service.interfaces;

import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.models.UserForCatsShelter;

public interface UserForCatsShelterService {

    UserForCatsShelter add(UserForCatsShelter userForCatsShelter);

    UserForCatsShelter read(long id);

    UserForCatsShelter update(UserForCatsShelter userForCatsShelter);

    UserForCatsShelter delete(long id);


}
