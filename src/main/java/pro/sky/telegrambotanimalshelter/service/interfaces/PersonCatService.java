package pro.sky.telegrambotanimalshelter.service.interfaces;


import pro.sky.telegrambotanimalshelter.models.PersonCat;

import java.util.Collection;


public interface PersonCatService {

    PersonCat getByIdPersonCat(Long id);

    PersonCat addPersonCat(PersonCat personCat);

    PersonCat updatePersonCat(PersonCat personCat);

    Collection<PersonCat> getAllPersonCat();

    void removeByIdPersonCat(Long id);
}