import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.DogNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.repository.DogRepository;
import pro.sky.telegrambotanimalshelter.service.implementation.DogServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DogServiceImplTest {

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogServiceImpl dogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByIdDog_ExistingDog_ReturnsDog() {
        // Arrange
        long dogId = 1L;
        Dog dog = new Dog();
        dog.setId(dogId);
        when(dogRepository.findById(dogId)).thenReturn(Optional.of(dog));

        // Act
        Dog result = dogService.getByIdDog(dogId);

        // Assert
        assertNotNull(result);
        assertEquals(dogId, result.getId());
    }

    @Test
    void getByIdDog_NonExistingDog_ThrowsException() {
        // Arrange
        long dogId = 1L;
        when(dogRepository.findById(dogId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DogNotFoundException.class, () -> dogService.getByIdDog(dogId));
    }

    @Test
    void addDog_ReturnsSavedDog() {
        // Arrange
        Dog dog = new Dog();
        when(dogRepository.save(dog)).thenReturn(dog);

        // Act
        Dog result = dogService.addDog(dog);

        // Assert
        assertNotNull(result);
    }

    @Test
    void updateDog_ExistingDog_ReturnsUpdatedDog() {
        // Arrange
        Dog dog = new Dog();
        long dogId = 1L;
        dog.setId(dogId);
        when(dogRepository.findById(dogId)).thenReturn(Optional.of(dog));
        when(dogRepository.save(dog)).thenReturn(dog);

        // Act
        Dog result = dogService.updateDog(dog);

        // Assert
        assertNotNull(result);
        assertEquals(dogId, result.getId());
    }

    @Test
    void updateDog_NonExistingDog_ThrowsException() {
        // Arrange
        Dog dog = new Dog();
        when(dogRepository.findById(dog.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DogNotFoundException.class, () -> dogService.updateDog(dog));
    }

    @Test
    void getAllDog_ReturnsListOfDogs() {
        // Arrange
        List<Dog> dogs = new ArrayList<>();
        when(dogRepository.findAll()).thenReturn(dogs);

        // Act
        List<Dog> result = (List<Dog>) dogService.getAllDog();

        // Assert
        assertNotNull(result);
        assertEquals(dogs, result);
    }

    @Test
    void removeByIdDog_RemovesDogById() {
        // Arrange
        long dogId = 1L;

        // Act
        dogService.removeByIdDog(dogId);

        // Assert
        verify(dogRepository, times(1)).deleteById(dogId);
    }
}
