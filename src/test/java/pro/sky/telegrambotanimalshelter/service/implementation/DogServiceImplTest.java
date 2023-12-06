package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.repository.DogRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DogServiceImplTest {

    @Mock
    private DogRepository repository;

    @InjectMocks
    private DogServiceImpl dogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdDog() {
        Long dogId = 1L;
        Dog testDog = new Dog();
        testDog.setId(dogId);

        when(repository.findById(dogId)).thenReturn(Optional.of(testDog));

        Dog result = dogService.getByIdDog(dogId);

        assertEquals(testDog, result);
    }

    @Test
    void testAddDog() {
        Dog testDog = new Dog();

        when(repository.save(testDog)).thenReturn(testDog);

        Dog result = dogService.addDog(testDog);

        assertEquals(testDog, result);

        verify(repository, times(1)).save(testDog);
    }

    @Test
    void testUpdateDog() {
        Long dogId = 1L;
        Dog testDog = new Dog();
        testDog.setId(dogId);

        when(repository.findById(dogId)).thenReturn(Optional.of(testDog));
        when(repository.save(testDog)).thenReturn(testDog);

        Dog result = dogService.updateDog(testDog);

        assertEquals(testDog, result);

        verify(repository, times(1)).save(testDog);
    }

    @Test
    void testGetAllDog() {
        Dog testDog = new Dog();

        when(repository.findAll()).thenReturn(Collections.singletonList(testDog));

        Collection<Dog> result = dogService.getAllDog();

        assertTrue(result.contains(testDog));
    }

    @Test
    void testRemoveByIdDog() {
        Long dogId = 1L;

        dogService.removeByIdDog(dogId);

        verify(repository, times(1)).deleteById(dogId);
    }
}
