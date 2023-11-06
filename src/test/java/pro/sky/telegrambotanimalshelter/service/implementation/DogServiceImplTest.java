package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.DogNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.repository.DogRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DogServiceImplTest {

    private DogServiceImpl dogService;

    @Mock
    private DogRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dogService = new DogServiceImpl(repository);
    }

    @Test
    public void testGetByIdDog_WithValidId_ReturnsDog() {
        Dog dog = new Dog();
        dog.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(dog));

        Dog result = dogService.getByIdDog(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetByIdDog_WithInvalidId_ThrowsException() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(DogNotFoundException.class, () -> dogService.getByIdDog(1L));
    }

    @Test
    public void testAddDog_AddsDog() {
        Dog dog = new Dog();
        when(repository.save(any(Dog.class))).thenReturn(dog);

        Dog result = dogService.addDog(dog);

        assertNotNull(result);
        assertEquals(dog, result);
    }

    @Test
    public void testUpdateDog_WithValidId_UpdatesDog() {
        Dog dog = new Dog();
        dog.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(dog));
        when(repository.save(any(Dog.class))).thenReturn(dog);

        Dog result = dogService.updateDog(dog);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateDog_WithInvalidId_ThrowsException() {
        Dog dog = new Dog();
        dog.setId(1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(DogNotFoundException.class, () -> dogService.updateDog(dog));
    }

    @Test
    public void testRemoveByIdDog_RemovesDog() {
        dogService.removeByIdDog(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllDog_ReturnsListOfDogs() {
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        when(repository.findAll()).thenReturn(List.of(dog1, dog2));

        Collection<Dog> result = dogService.getAllDog();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    // Дополните тесты для других методов по аналогии
}
