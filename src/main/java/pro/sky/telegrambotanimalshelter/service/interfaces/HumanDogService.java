package pro.sky.telegrambotanimalshelter.service.interfaces;


import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.HumanDog;

import java.util.Collection;

@Service
public interface HumanDogService {
    HumanDog getByIdHumanDog(Long id);

    HumanDog addHumanDog(HumanDog personDog);

    HumanDog updateHumanDog(HumanDog personDog);

    Collection<HumanDog> getAllHumanDog();

    void removeByIdHumanDog(Long id);
    HumanDog findByChatId(long chatId);
    HumanDog saveDog(HumanDog humanDog);
    boolean existsByChatId(Long chatId);
}