package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatServiceImplTest {

    @Mock
    private CatRepository repository;

    @InjectMocks
    private CatServiceImpl catService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdCat() {
        Long catId = 1L;
        Cat testCat = new Cat();
        testCat.setId(catId);

        when(repository.findById(catId)).thenReturn(Optional.of(testCat));

        Cat result = catService.getByIdCat(catId);

        assertEquals(testCat, result);
    }

    @Test
    void testAddCat() {
        Cat testCat = new Cat();

        when(repository.save(testCat)).thenReturn(testCat);

        Cat result = catService.addCat(testCat);

        assertEquals(testCat, result);

        verify(repository, times(1)).save(testCat);
    }

    @Test
    void testUpdateCat() {
        Long catId = 1L;
        Cat testCat = new Cat();
        testCat.setId(catId);

        when(repository.findById(catId)).thenReturn(Optional.of(testCat));
        when(repository.save(testCat)).thenReturn(testCat);

        Cat result = catService.updateCat(testCat);

        assertEquals(testCat, result);

        verify(repository, times(1)).save(testCat);
    }

    @Test
    void testGetAllCat() {
        Cat testCat = new Cat();

        when(repository.findAll()).thenReturn(Collections.singletonList(testCat));

        Collection<Cat> result = catService.getAllCat();

        assertTrue(result.contains(testCat));
    }

    @Test
    void testRemoveByIdCat() {
        Long catId = 1L;

        catService.removeByIdCat(catId);

        verify(repository, times(1)).deleteById(catId);
    }
}
