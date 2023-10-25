package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.telegrambotanimalshelter.exceptions.CatException;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CatServiceImplTest {

    @InjectMocks
    private CatServiceImpl catService;

    @Mock
    private CatRepository catRepository;

    @Test
    public void addCat_NonExistingCat_SaveAndReturnCat() {
        Cat cat = new Cat();
        cat.setName("Whiskers");

        Mockito.when(catRepository.findByName(cat.getName())).thenReturn(Optional.empty());
        Mockito.when(catRepository.save(cat)).thenReturn(cat);

        Cat addedCat = catService.add(cat);
        assertNotNull(addedCat);
        assertEquals(cat, addedCat);
    }

    @Test
    public void addCat_ExistingCat_ThrowException() {
        Cat cat = new Cat();
        cat.setName("Whiskers");

        Mockito.when(catRepository.findByName(cat.getName())).thenReturn(Optional.of(cat));

        assertThrows(CatException.class, () -> catService.add(cat));
    }

    @Test
    public void readCat_ExistingCat_ReturnCat() {
        long id = 1;
        Cat cat = new Cat();
        cat.setId(id);

        Mockito.when(catRepository.findById(id)).thenReturn(Optional.of(cat));

        Cat readCat = catService.read(id);
        assertNotNull(readCat);
        assertEquals(cat, readCat);
    }

    @Test
    public void readCat_NonExistingCat_ThrowException() {
        long id = 1;

        Mockito.when(catRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CatException.class, () -> catService.read(id));
    }

    @Test
    public void updateCat_ExistingCat_UpdateAndReturnCat() {
        Cat cat = new Cat();
        cat.setId(1L);

        Mockito.when(catRepository.existsById(cat.getId())).thenReturn(true);
        Mockito.when(catRepository.save(cat)).thenReturn(cat);

        Cat updatedCat = catService.update(cat);
        assertNotNull(updatedCat);
        assertEquals(cat, updatedCat);
    }

    @Test
    public void updateCat_NonExistingCat_ThrowException() {
        Cat cat = new Cat();
        cat.setId(1L);

        Mockito.when(catRepository.existsById(cat.getId())).thenReturn(false);

        assertThrows(CatException.class, () -> catService.update(cat));
    }

    @Test
    public void deleteCat_ExistingCat_DeleteAndReturnCat() {
        long id = 1;
        Cat cat = new Cat();
        cat.setId(id);

        Mockito.when(catRepository.findById(id)).thenReturn(Optional.of(cat));
        Mockito.when(catRepository.save(cat)).thenReturn(cat);

        Cat deletedCat = catService.delete(id);
        assertNotNull(deletedCat);
        assertEquals(cat, deletedCat);
    }

    @Test
    public void deleteCat_NonExistingCat_ThrowException() {
        long id = 1;

        Mockito.when(catRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CatException.class, () -> catService.delete(id));
    }

    @Test
    public void findAllCats_ExistingCats_ReturnListOfCats() {
        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        catList.add(new Cat());

        Mockito.when(catRepository.findAll()).thenReturn(catList);

        List<Cat> foundCats = catService.findAll();
        assertNotNull(foundCats);
        assertEquals(catList, foundCats);
    }
}
