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
public class HumanDogServiceImplTest {
    private static final String PERSON_NAME = "Дима";
    private static final int YEAR_OF_BIRTH = 2001;
    private static final String PHONE = "+77777777777";
    private static final String ADDRESS = "Швеция";
    private static final Long CHAT_ID = 1L;
    private static final Status STATUS = Status.SEARCH;

    private static final List<HumanDog> humanDogs = new ArrayList<>(Arrays.asList(
            new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS),
            new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS),
            new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS)));
    @Mock
    private HumanDogRepository humanDogRepositoryMock;

    @InjectMocks
    private HumanDogServiceImpl humanDogService;

    private final HumanDog humanDog = new HumanDog(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS);


    @Test
    public void getByIdPersonDog() {
        Mockito.when(humanDogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(humanDog));
        HumanDog dog = humanDogService.getByIdHumanDog(1L);
        Assertions.assertThat(dog.getId()).isEqualTo(humanDog.getId());
        Assertions.assertThat(dog.getName()).isEqualTo(humanDog.getName());
        Assertions.assertThat(dog.getYearOfBirth()).isEqualTo(humanDog.getYearOfBirth());
        Assertions.assertThat(dog.getPhone()).isEqualTo(humanDog.getPhone());
        Assertions.assertThat(dog.getAddress()).isEqualTo(humanDog.getAddress());
        Assertions.assertThat(dog.getChatId()).isEqualTo(humanDog.getChatId());
    }


    @Test
    public void addPersonDog() {
        Mockito.when(humanDogRepositoryMock.save(any(HumanDog.class))).thenReturn(humanDog);
        HumanDog humanDog1 = humanDogService.addHumanDog(humanDog);
        Assertions.assertThat(humanDog1.getId()).isEqualTo(humanDog.getId());
        Assertions.assertThat(humanDog1.getName()).isEqualTo(humanDog.getName());
        Assertions.assertThat(humanDog1.getYearOfBirth()).isEqualTo(humanDog.getYearOfBirth());
        Assertions.assertThat(humanDog1.getPhone()).isEqualTo(humanDog.getPhone());
        Assertions.assertThat(humanDog1.getAddress()).isEqualTo(humanDog.getAddress());
        Assertions.assertThat(humanDog1.getChatId()).isEqualTo(humanDog.getChatId());
    }


    @Test
    public void updateHumanDog() {
        HumanDog humanDog1Dog1 = new HumanDog();
        humanDog1Dog1.setName(PERSON_NAME);
        humanDog1Dog1.setYearOfBirth(YEAR_OF_BIRTH);
        humanDog1Dog1.setPhone(PHONE);
        humanDog1Dog1.setAddress(ADDRESS);
        humanDog1Dog1.setChatId(CHAT_ID);
        humanDog1Dog1.setStatus(STATUS);
        humanDog1Dog1.setId(1L);
        Mockito.when(humanDogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(humanDog1Dog1));
        Mockito.when(humanDogRepositoryMock.save(any(HumanDog.class))).thenReturn(humanDog1Dog1);
        HumanDog humanDog2 = humanDogService.updateHumanDog(humanDog1Dog1);
        Assertions.assertThat(humanDog2.getName()).isEqualTo(humanDog1Dog1.getName());
        Assertions.assertThat(humanDog2.getYearOfBirth()).isEqualTo(humanDog1Dog1.getYearOfBirth());
        Assertions.assertThat(humanDog2.getPhone()).isEqualTo(humanDog1Dog1.getPhone());
        Assertions.assertThat(humanDog2.getAddress()).isEqualTo(humanDog1Dog1.getAddress());
        Assertions.assertThat(humanDog2.getChatId()).isEqualTo(humanDog1Dog1.getChatId());
        Assertions.assertThat(humanDog2.getStatus()).isEqualTo(humanDog1Dog1.getStatus());
    }

    @Test
    public void getAllHumanDog() {
        Mockito.when(humanDogRepositoryMock.findAll()).thenReturn(humanDogs);
        Collection<HumanDog> humanDog1 = humanDogService.getAllHumanDog();
        Assertions.assertThat(humanDog1.size()).isEqualTo(humanDogs.size());
        Assertions.assertThat(humanDog1).isEqualTo(humanDogs);
    }
}
