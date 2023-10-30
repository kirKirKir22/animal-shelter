package pro.sky.telegrambotanimalshelter.service.interfaces;



import pro.sky.telegrambotanimalshelter.models.HumanDog;

import java.util.Collection;


public interface HumanDogService {
    HumanDog getByIdHumanDog(Long id);

    HumanDog addHumanDog(HumanDog personDog);

    HumanDog updateHumanDog(HumanDog personDog);

    Collection<HumanDog> getAllHumanDog();

    void removeByIdHumanDog(Long id);
}