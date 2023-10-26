package pro.sky.telegrambotanimalshelter.service.interfaces;


import pro.sky.telegrambotanimalshelter.models.UserForCatsShelter;

import java.util.List;

public interface UserForCatsShelterService {

    UserForCatsShelter add(UserForCatsShelter userForCatsShelter);

    UserForCatsShelter read(long id);

    UserForCatsShelter update(UserForCatsShelter userForCatsShelter);

    UserForCatsShelter delete(long id);

    List<UserForCatsShelter> findAll();


}
