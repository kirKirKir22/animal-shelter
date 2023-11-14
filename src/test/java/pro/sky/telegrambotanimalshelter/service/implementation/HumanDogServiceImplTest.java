package pro.sky.telegrambotanimalshelter.service.implementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.exceptions.HumanDogNotFoundException;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.repository.HumanDogRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HumanDogServiceImplTest {

    private HumanDogService humanDogService;

    @Mock
    private HumanDogRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        humanDogService = new HumanDogService(repository);
    }

    @Test
    public void testGetByIdHumanDog_WithValidId_ReturnsHumanDog() {
        HumanDog humanDog = new HumanDog();
        humanDog.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(humanDog));

        HumanDog result = humanDogService.getByIdHumanDog(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetByIdHumanDog_WithInvalidId_ThrowsException() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(HumanDogNotFoundException.class, () -> humanDogService.getByIdHumanDog(1L));
    }

    @Test
    public void testAddHumanDog_AddsHumanDog() {
        HumanDog humanDog = new HumanDog();
        when(repository.save(any(HumanDog.class))).thenReturn(humanDog);

        HumanDog result = humanDogService.addHumanDog(humanDog);

        assertNotNull(result);
        assertEquals(humanDog, result);
    }

    @Test
    public void testUpdateHumanDog_WithValidId_UpdatesHumanDog() {
        HumanDog humanDog = new HumanDog();
        humanDog.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(humanDog));
        when(repository.save(any(HumanDog.class))).thenReturn(humanDog);

        HumanDog result = humanDogService.updateHumanDog(humanDog);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateHumanDog_WithInvalidId_ThrowsException() {
        HumanDog humanDog = new HumanDog();
        humanDog.setId(1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(HumanDogNotFoundException.class, () -> humanDogService.updateHumanDog(humanDog));
    }

    @Test
    public void testRemoveByIdHumanDog_RemovesHumanDog() {
        humanDogService.removeByIdHumanDog(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByChatId_WithExistingChatId_ReturnsHumanDog() {
        HumanDog humanDog = new HumanDog();
        long chatId = 123L;
        when(repository.findByChatId(chatId)).thenReturn(humanDog);

        HumanDog result = humanDogService.findByChatId(chatId);

        assertNotNull(result);
        assertEquals(humanDog, result);
    }

    @Test
    public void testFindByChatId_WithNonExistingChatId_ReturnsNull() {
        long chatId = 123L;
        when(repository.findByChatId(chatId)).thenReturn(null);

        HumanDog result = humanDogService.findByChatId(chatId);

        assertNull(result);
    }

    @Test
    public void testSaveDog_SavesHumanDog() {
        HumanDog humanDog = new HumanDog();
        when(repository.save(any(HumanDog.class))).thenReturn(humanDog);

        HumanDog result = humanDogService.saveDog(humanDog);

        assertNotNull(result);
        assertEquals(humanDog, result);
    }

    @Test
    public void testFindAll_ReturnsListOfHumanDogs() {
        HumanDog humanDog1 = new HumanDog();
        HumanDog humanDog2 = new HumanDog();
        when(repository.findAll()).thenReturn(List.of(humanDog1, humanDog2));

        List<HumanDog> result = humanDogService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

}