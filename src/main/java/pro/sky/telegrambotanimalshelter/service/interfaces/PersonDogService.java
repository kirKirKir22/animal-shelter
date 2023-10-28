package pro.sky.telegrambotanimalshelter.service.interfaces;



import pro.sky.telegrambotanimalshelter.models.PersonDog;

import java.util.Collection;


public interface PersonDogService {
    PersonDog getByIdPersonDog(Long id);

    PersonDog addPersonDog(PersonDog personDog);

    PersonDog updatePersonDog(PersonDog personDog);

    Collection<PersonDog> getAllPersonDog();

    void removeByIdPersonDog(Long id);
}