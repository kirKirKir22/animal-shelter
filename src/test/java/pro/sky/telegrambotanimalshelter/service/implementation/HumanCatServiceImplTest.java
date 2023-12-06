package pro.sky.telegrambotanimalshelter.service.implementation;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.repository.HumanCatRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HumanCatServiceImplTest {

    @Mock
    private HumanCatRepository repository;

    @InjectMocks
    private HumanCatServiceImpl humanCatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdHumanCat() {
        Long humanCatId = 1L;
        HumanCat testHumanCat = new HumanCat();
        testHumanCat.setId(humanCatId);

        when(repository.findById(humanCatId)).thenReturn(Optional.of(testHumanCat));

        HumanCat result = humanCatService.getByIdHumanCat(humanCatId);

        assertEquals(testHumanCat, result);
    }

    @Test
    void testAddHumanCat() {
        HumanCat testHumanCat = new HumanCat();

        when(repository.save(testHumanCat)).thenReturn(testHumanCat);

        HumanCat result = humanCatService.addHumanCat(testHumanCat);

        assertEquals(testHumanCat, result);

        verify(repository, times(1)).save(testHumanCat);
    }

    @Test
    void testUpdateHumanCat() {
        Long humanCatId = 1L;
        HumanCat testHumanCat = new HumanCat();
        testHumanCat.setId(humanCatId);

        when(repository.findById(humanCatId)).thenReturn(Optional.of(testHumanCat));
        when(repository.save(testHumanCat)).thenReturn(testHumanCat);

        HumanCat result = humanCatService.updateHumanCat(testHumanCat);

        assertEquals(testHumanCat, result);

        verify(repository, times(1)).save(testHumanCat);
    }

    @Test
    void testGetAllHumanCat() {
        HumanCat testHumanCat = new HumanCat();

        when(repository.findAll()).thenReturn(Collections.singletonList(testHumanCat));

        Collection<HumanCat> result = humanCatService.getAllHumanCat();

        assertTrue(result.contains(testHumanCat));
    }

    @Test
    void testRemoveByIdHumanCat() {
        Long humanCatId = 1L;

        humanCatService.removeByIdHumanCat(humanCatId);

        verify(repository, times(1)).deleteById(humanCatId);
    }

    @Test
    void testFindByChatId() {
        long chatId = 123456789L;
        HumanCat testHumanCat = new HumanCat();

        when(repository.findByChatId(chatId)).thenReturn(testHumanCat);

        HumanCat result = humanCatService.findByChatId(chatId);

        assertEquals(testHumanCat, result);
    }

    @Test
    void testSaveCat() {
        HumanCat testHumanCat = new HumanCat();

        when(repository.save(testHumanCat)).thenReturn(testHumanCat);

        HumanCat result = humanCatService.saveCat(testHumanCat);

        assertEquals(testHumanCat, result);

        verify(repository, times(1)).save(testHumanCat);
    }

    @Test
    void testExistsByChatId() {
        Long chatId = 123456789L;

        when(repository.existsByChatId(chatId)).thenReturn(true);

        boolean result = humanCatService.existsByChatId(chatId);

        assertTrue(result);
    }

    @Test
    void testFindAll() {
        HumanCat testHumanCat = new HumanCat();

        when(repository.findAll()).thenReturn(Collections.singletonList(testHumanCat));

        List<HumanCat> result = humanCatService.findAll();

        assertTrue(result.contains(testHumanCat));
    }
}
