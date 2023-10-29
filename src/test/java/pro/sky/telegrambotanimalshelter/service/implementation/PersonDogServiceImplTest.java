package pro.sky.telegrambotanimalshelter.service.implementation;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.models.Status;
import pro.sky.telegrambotanimalshelter.repository.HumanDogRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class PersonDogServiceImplTest {
    private static final String PERSON_NAME = "Дима";
    private static final int YEAR_OF_BIRTH = 2001;
    private static final String PHONE = "+77777777777";
    private static final String ADDRESS = "Швеция";
    private static final Long CHAT_ID = 1L;
    private static final Status STATUS = Status.SEARCH;

    private static final List<HumanDog> personDogs = new ArrayList<>(Arrays.asList(
            new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS),
            new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS),
            new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS)));
    @Mock
    private HumanDogRepository personDogRepositoryMock;

    @InjectMocks
    private HumanDogServiceImpl personDogService;

    private final HumanDog personDog = new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS);


    @Test
    public void getByIdPersonDog() {
        Mockito.when(personDogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(personDog));
        HumanDog dog = personDogService.getByIdHumanDog(1L);
        Assertions.assertThat(dog.getId()).isEqualTo(personDog.getId());
        Assertions.assertThat(dog.getName()).isEqualTo(personDog.getName());
        Assertions.assertThat(dog.getYearOfBirth()).isEqualTo(personDog.getYearOfBirth());
        Assertions.assertThat(dog.getPhone()).isEqualTo(personDog.getPhone());
        Assertions.assertThat(dog.getAddress()).isEqualTo(personDog.getAddress());
        Assertions.assertThat(dog.getChatId()).isEqualTo(personDog.getChatId());
    }


    @Test
    public void addPersonDog() {
        Mockito.when(personDogRepositoryMock.save(any(HumanDog.class))).thenReturn(personDog);
        HumanDog personDog1 = personDogService.addHumanDog(personDog);
        Assertions.assertThat(personDog1.getId()).isEqualTo(personDog.getId());
        Assertions.assertThat(personDog1.getName()).isEqualTo(personDog.getName());
        Assertions.assertThat(personDog1.getYearOfBirth()).isEqualTo(personDog.getYearOfBirth());
        Assertions.assertThat(personDog1.getPhone()).isEqualTo(personDog.getPhone());
        Assertions.assertThat(personDog1.getAddress()).isEqualTo(personDog.getAddress());
        Assertions.assertThat(personDog1.getChatId()).isEqualTo(personDog.getChatId());
    }


    @Test
    public void updatePersonDog() {
        HumanDog personDog1 = new HumanDog();
        personDog1.setName(PERSON_NAME);
        personDog1.setYearOfBirth(YEAR_OF_BIRTH);
        personDog1.setPhone(PHONE);
        personDog1.setAddress(ADDRESS);
        personDog1.setChatId(CHAT_ID);
        personDog1.setStatus(STATUS);
        personDog1.setId(1L);
        Mockito.when(personDogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(personDog1));
        Mockito.when(personDogRepositoryMock.save(any(HumanDog.class))).thenReturn(personDog1);
        HumanDog personDog2 = personDogService.updateHumanDog(personDog1);
        Assertions.assertThat(personDog2.getName()).isEqualTo(personDog1.getName());
        Assertions.assertThat(personDog2.getYearOfBirth()).isEqualTo(personDog1.getYearOfBirth());
        Assertions.assertThat(personDog2.getPhone()).isEqualTo(personDog1.getPhone());
        Assertions.assertThat(personDog2.getAddress()).isEqualTo(personDog1.getAddress());
        Assertions.assertThat(personDog2.getChatId()).isEqualTo(personDog1.getChatId());
        Assertions.assertThat(personDog2.getStatus()).isEqualTo(personDog1.getStatus());
    }

    @Test
    public void getAllPersonDog() {
        Mockito.when(personDogRepositoryMock.findAll()).thenReturn(personDogs);
        Collection<HumanDog> personDog1 = personDogService.getAllHumanDog();
        Assertions.assertThat(personDog1.size()).isEqualTo(personDogs.size());
        Assertions.assertThat(personDog1).isEqualTo(personDogs);
    }
}
