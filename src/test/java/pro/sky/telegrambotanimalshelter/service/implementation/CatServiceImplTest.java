package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.CatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.repository.CatRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CatServiceImplTest {

    private CatServiceImpl catService;

    @Mock
    private CatRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        catService = new CatServiceImpl(repository);
    }

    @Test
    public void testGetByIdCat_WithValidId_ReturnsCat() {
        Cat cat = new Cat();
        cat.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(cat));

        Cat result = catService.getByIdCat(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetByIdCat_WithInvalidId_ThrowsException() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(CatNotFoundException.class, () -> catService.getByIdCat(1L));
    }

    @Test
    public void testAddCat_AddsCat() {
        Cat cat = new Cat();
        when(repository.save(any(Cat.class))).thenReturn(cat);

        Cat result = catService.addCat(cat);

        assertNotNull(result);
        assertEquals(cat, result);
    }

    @Test
    public void testUpdateCat_WithValidId_UpdatesCat() {
        Cat cat = new Cat();
        cat.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(cat));
        when(repository.save(any(Cat.class))).thenReturn(cat);

        Cat result = catService.updateCat(cat);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateCat_WithInvalidId_ThrowsException() {
        Cat cat = new Cat();
        cat.setId(1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(CatNotFoundException.class, () -> catService.updateCat(cat));
    }

    @Test
    public void testRemoveByIdCat_RemovesCat() {
        catService.removeByIdCat(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllCat_ReturnsListOfCats() {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        when(repository.findAll()).thenReturn(List.of(cat1, cat2));

        Collection<Cat> result = catService.getAllCat();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

}
