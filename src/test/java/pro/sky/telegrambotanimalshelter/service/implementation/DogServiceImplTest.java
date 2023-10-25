package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.telegrambotanimalshelter.exceptions.DogException;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class DogServiceImplTest {

    @InjectMocks
    private DogServiceImpl dogService;

    @Mock
    private DogRepository dogRepository;

    @Test
    public void addDog_NonExistingDog_SaveAndReturnDog() {
        Dog dog = new Dog();
        dog.setName("Buddy");

        Mockito.when(dogRepository.findByName(dog.getName())).thenReturn(Optional.empty());
        Mockito.when(dogRepository.save(dog)).thenReturn(dog);

        Dog addedDog = dogService.add(dog);
        assertNotNull(addedDog);
        assertEquals(dog, addedDog);
    }

    @Test
    public void addDog_ExistingDog_ThrowException() {
        Dog dog = new Dog();
        dog.setName("Buddy");

        Mockito.when(dogRepository.findByName(dog.getName())).thenReturn(Optional.of(dog));

        assertThrows(DogException.class, () -> dogService.add(dog));
    }

    @Test
    public void readDog_ExistingDog_ReturnDog() {
        long id = 1;
        Dog dog = new Dog();
        dog.setId(id);

        Mockito.when(dogRepository.findById(id)).thenReturn(Optional.of(dog));

        Dog readDog = dogService.read(id);
        assertNotNull(readDog);
        assertEquals(dog, readDog);
    }

    @Test
    public void readDog_NonExistingDog_ThrowException() {
        long id = 1;

        Mockito.when(dogRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DogException.class, () -> dogService.read(id));
    }

    @Test
    public void updateDog_ExistingDog_UpdateAndReturnDog() {
        Dog dog = new Dog();
        dog.setId(1L);

        Mockito.when(dogRepository.existsById(dog.getId())).thenReturn(true);
        Mockito.when(dogRepository.save(dog)).thenReturn(dog);

        Dog updatedDog = dogService.update(dog);
        assertNotNull(updatedDog);
        assertEquals(dog, updatedDog);
    }

    @Test
    public void updateDog_NonExistingDog_ThrowException() {
        Dog dog = new Dog();
        dog.setId(1L);

        Mockito.when(dogRepository.existsById(dog.getId())).thenReturn(false);

        assertThrows(DogException.class, () -> dogService.update(dog));
    }

    @Test
    public void deleteDog_ExistingDog_DeleteAndReturnDog() {
        long id = 1;
        Dog dog = new Dog();
        dog.setId(id);

        Mockito.when(dogRepository.findById(id)).thenReturn(Optional.of(dog));
        Mockito.when(dogRepository.save(dog)).thenReturn(dog);

        Dog deletedDog = dogService.delete(id);
        assertNotNull(deletedDog);
        assertEquals(dog, deletedDog);
    }

    @Test
    public void deleteDog_NonExistingDog_ThrowException() {
        long id = 1;

        Mockito.when(dogRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DogException.class, () -> dogService.delete(id));
    }

    @Test
    public void findAllDogs_ExistingDogs_ReturnListOfDogs() {
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog());
        dogList.add(new Dog());

        Mockito.when(dogRepository.findAll()).thenReturn(dogList);

        List<Dog> foundDogs = dogService.findAll();
        assertNotNull(foundDogs);
        assertEquals(dogList, foundDogs);
    }
}
