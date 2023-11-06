package pro.sky.telegrambotanimalshelter.service.implementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.HumanCatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.repository.HumanCatRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HumanCatServiceImplTest {

    private HumanCatServiceImpl humanCatService;

    @Mock
    private HumanCatRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        humanCatService = new HumanCatServiceImpl(repository);
    }

    @Test
    public void testGetByIdHumanCat_WithValidId_ReturnsHumanCat() {
        HumanCat humanCat = new HumanCat();
        humanCat.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(humanCat));

        HumanCat result = humanCatService.getByIdHumanCat(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetByIdHumanCat_WithInvalidId_ThrowsException() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(HumanCatNotFoundException.class, () -> humanCatService.getByIdHumanCat(1L));
    }

    @Test
    public void testAddHumanCat_AddsHumanCat() {
        HumanCat humanCat = new HumanCat();
        when(repository.save(any(HumanCat.class))).thenReturn(humanCat);

        HumanCat result = humanCatService.addHumanCat(humanCat);

        assertNotNull(result);
        assertEquals(humanCat, result);
    }

    @Test
    public void testUpdateHumanCat_WithValidId_UpdatesHumanCat() {
        HumanCat humanCat = new HumanCat();
        humanCat.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(humanCat));
        when(repository.save(any(HumanCat.class))).thenReturn(humanCat);

        HumanCat result = humanCatService.updateHumanCat(humanCat);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateHumanCat_WithInvalidId_ThrowsException() {
        HumanCat humanCat = new HumanCat();
        humanCat.setId(1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(HumanCatNotFoundException.class, () -> humanCatService.updateHumanCat(humanCat));
    }

    @Test
    public void testRemoveByIdHumanCat_RemovesHumanCat() {
        humanCatService.removeByIdHumanCat(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByChatId_WithExistingChatId_ReturnsHumanCat() {
        HumanCat humanCat = new HumanCat();
        long chatId = 123L;
        when(repository.findByChatId(chatId)).thenReturn(humanCat);

        HumanCat result = humanCatService.findByChatId(chatId);

        assertNotNull(result);
        assertEquals(humanCat, result);
    }

    @Test
    public void testFindByChatId_WithNonExistingChatId_ReturnsNull() {
        long chatId = 123L;
        when(repository.findByChatId(chatId)).thenReturn(null);

        HumanCat result = humanCatService.findByChatId(chatId);

        assertNull(result);
    }

    @Test
    public void testSaveCat_SavesHumanCat() {
        HumanCat humanCat = new HumanCat();
        when(repository.save(any(HumanCat.class))).thenReturn(humanCat);

        HumanCat result = humanCatService.saveCat(humanCat);

        assertNotNull(result);
        assertEquals(humanCat, result);
    }

    @Test
    public void testFindAll_ReturnsListOfHumanCats() {
        HumanCat humanCat1 = new HumanCat();
        HumanCat humanCat2 = new HumanCat();
        when(repository.findAll()).thenReturn(List.of(humanCat1, humanCat2));

        List<HumanCat> result = humanCatService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
