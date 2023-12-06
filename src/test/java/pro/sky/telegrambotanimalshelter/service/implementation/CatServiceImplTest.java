package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.CatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatServiceImplTest {

    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private CatServiceImpl catService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByIdCat_ExistingCat_ReturnsCat() {
        // Arrange
        long catId = 1L;
        Cat cat = new Cat();
        cat.setId(catId);
        when(catRepository.findById(catId)).thenReturn(Optional.of(cat));

        // Act
        Cat result = catService.getByIdCat(catId);

        // Assert
        assertNotNull(result);
        assertEquals(catId, result.getId());
    }

    @Test
    void getByIdCat_NonExistingCat_ThrowsException() {
        // Arrange
        long catId = 1L;
        when(catRepository.findById(catId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CatNotFoundException.class, () -> catService.getByIdCat(catId));
    }

    @Test
    void addCat_ReturnsSavedCat() {
        // Arrange
        Cat cat = new Cat();
        when(catRepository.save(cat)).thenReturn(cat);

        // Act
        Cat result = catService.addCat(cat);

        // Assert
        assertNotNull(result);
    }

    @Test
    void updateCat_ExistingCat_ReturnsUpdatedCat() {
        // Arrange
        Cat cat = new Cat();
        long catId = 1L;
        cat.setId(catId);
        when(catRepository.findById(catId)).thenReturn(Optional.of(cat));
        when(catRepository.save(cat)).thenReturn(cat);

        // Act
        Cat result = catService.updateCat(cat);

        // Assert
        assertNotNull(result);
        assertEquals(catId, result.getId());
    }

    @Test
    void updateCat_NonExistingCat_ThrowsException() {
        // Arrange
        Cat cat = new Cat();
        when(catRepository.findById(cat.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CatNotFoundException.class, () -> catService.updateCat(cat));
    }

    @Test
    void getAllCat_ReturnsListOfCats() {
        // Arrange
        List<Cat> cats = new ArrayList<>();
        when(catRepository.findAll()).thenReturn(cats);

        // Act
        List<Cat> result = (List<Cat>) catService.getAllCat();

        // Assert
        assertNotNull(result);
        assertEquals(cats, result);
    }

    @Test
    void removeByIdCat_RemovesCatById() {
        // Arrange
        long catId = 1L;

        // Act
        catService.removeByIdCat(catId);

        // Assert
        verify(catRepository, times(1)).deleteById(catId);
    }
}
