package pro.sky.telegrambotanimalshelter.service.implementation;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.models.Status;
import pro.sky.telegrambotanimalshelter.repository.HumanCatRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class HumanCatServiceImplTest {
    private static final String PERSON_NAME = "Артём";
    private static final int YEAR_OF_BIRTH = 2000;
    private static final String PHONE = "+77777777777";
    private static final String ADDRESS = "Британия";
    private static final Long CHAT_ID = 5L;
    private static final Status STATUS = Status.APPROVED;

    private static final List<HumanCat> humanCats = new ArrayList<>(Arrays.asList(
            new HumanCat(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS),
            new HumanCat(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS),
            new HumanCat(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS)));
    @Mock
    private HumanCatRepository humanCatRepositoryMock;

    @InjectMocks
    private HumanCatServiceImpl humanCatService;

    private final HumanCat humanCat = new HumanCat(PERSON_NAME, YEAR_OF_BIRTH, PHONE, ADDRESS, CHAT_ID, STATUS);


    @Test
    public void getByIdHumanCat() {
        Mockito.when(humanCatRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(humanCat));
        HumanCat cat = humanCatService.getByIdHumanCat(1L);
        Assertions.assertThat(cat.getId()).isEqualTo(humanCat.getId());
        Assertions.assertThat(cat.getName()).isEqualTo(humanCat.getName());
        Assertions.assertThat(cat.getYearOfBirth()).isEqualTo(humanCat.getYearOfBirth());
        Assertions.assertThat(cat.getPhone()).isEqualTo(humanCat.getPhone());
        Assertions.assertThat(cat.getAddress()).isEqualTo(humanCat.getAddress());
        Assertions.assertThat(cat.getChatId()).isEqualTo(humanCat.getChatId());
    }


    @Test
    public void addPersonCat() {
        Mockito.when(humanCatRepositoryMock.save(any(HumanCat.class))).thenReturn(humanCat);
        HumanCat cat = humanCatService.addHumanCat(humanCat);
        Assertions.assertThat(cat.getId()).isEqualTo(humanCat.getId());
        Assertions.assertThat(cat.getName()).isEqualTo(humanCat.getName());
        Assertions.assertThat(cat.getYearOfBirth()).isEqualTo(humanCat.getYearOfBirth());
        Assertions.assertThat(cat.getPhone()).isEqualTo(humanCat.getPhone());
        Assertions.assertThat(cat.getAddress()).isEqualTo(humanCat.getAddress());
        Assertions.assertThat(cat.getChatId()).isEqualTo(humanCat.getChatId());
    }


    @Test
    public void updateHumanCat() {
        HumanCat humanCat = new HumanCat();
        humanCat.setName(PERSON_NAME);
        humanCat.setYearOfBirth(YEAR_OF_BIRTH);
        humanCat.setPhone(PHONE);
        humanCat.setAddress(ADDRESS);
        humanCat.setChatId(CHAT_ID);
        humanCat.setStatus(STATUS);
        humanCat.setId(1L);
        Mockito.when(humanCatRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(humanCat));
        Mockito.when(humanCatRepositoryMock.save(any(HumanCat.class))).thenReturn(humanCat);
        HumanCat humanCat2 = humanCatService.updateHumanCat(humanCat);
        Assertions.assertThat(humanCat.getName()).isEqualTo(humanCat.getName());
        Assertions.assertThat(humanCat.getYearOfBirth()).isEqualTo(humanCat.getYearOfBirth());
        Assertions.assertThat(humanCat.getPhone()).isEqualTo(humanCat.getPhone());
        Assertions.assertThat(humanCat.getAddress()).isEqualTo(humanCat.getAddress());
        Assertions.assertThat(humanCat.getChatId()).isEqualTo(humanCat.getChatId());
        Assertions.assertThat(humanCat.getStatus()).isEqualTo(humanCat.getStatus());
    }


    @Test
    public void getAllHumanCat() {
        Mockito.when(humanCatRepositoryMock.findAll()).thenReturn(humanCats);
        Collection<HumanCat> cat = humanCatService.getAllHumanCat();
        Assertions.assertThat(cat.size()).isEqualTo(humanCats.size());
        Assertions.assertThat(cat).isEqualTo(humanCats);
    }
}
