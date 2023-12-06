package pro.sky.telegrambotanimalshelter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.repository.HumanDogRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HumanDogServiceImplTest {

    @Mock
    private HumanDogRepository repository;

    @InjectMocks
    private HumanDogServiceImpl humanDogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdHumanDog() {
        Long humanDogId = 1L;
        HumanDog testHumanDog = new HumanDog();
        testHumanDog.setId(humanDogId);

        when(repository.findById(humanDogId)).thenReturn(Optional.of(testHumanDog));

        HumanDog result = humanDogService.getByIdHumanDog(humanDogId);

        assertEquals(testHumanDog, result);
    }

    @Test
    void testAddHumanDog() {
        HumanDog testHumanDog = new HumanDog();

        when(repository.save(testHumanDog)).thenReturn(testHumanDog);

        HumanDog result = humanDogService.addHumanDog(testHumanDog);

        assertEquals(testHumanDog, result);

        verify(repository, times(1)).save(testHumanDog);
    }

    @Test
    void testUpdateHumanDog() {
        Long humanDogId = 1L;
        HumanDog testHumanDog = new HumanDog();
        testHumanDog.setId(humanDogId);

        when(repository.findById(humanDogId)).thenReturn(Optional.of(testHumanDog));
        when(repository.save(testHumanDog)).thenReturn(testHumanDog);

        HumanDog result = humanDogService.updateHumanDog(testHumanDog);

        assertEquals(testHumanDog, result);

        verify(repository, times(1)).save(testHumanDog);
    }

    @Test
    void testGetAllHumanDog() {
        HumanDog testHumanDog = new HumanDog();

        when(repository.findAll()).thenReturn(Collections.singletonList(testHumanDog));

        Collection<HumanDog> result = humanDogService.getAllHumanDog();

        assertEquals(1, result.size());
        assertTrue(result.contains(testHumanDog));
    }

    @Test
    void testRemoveByIdHumanDog() {
        Long humanDogId = 1L;

        humanDogService.removeByIdHumanDog(humanDogId);

        verify(repository, times(1)).deleteById(humanDogId);
    }

    @Test
    void testFindByChatId() {
        long chatId = 123456789L;
        HumanDog testHumanDog = new HumanDog();

        when(repository.findByChatId(chatId)).thenReturn(testHumanDog);

        HumanDog result = humanDogService.findByChatId(chatId);

        assertEquals(testHumanDog, result);
    }

    @Test
    void testSaveDog() {
        HumanDog testHumanDog = new HumanDog();

        when(repository.save(testHumanDog)).thenReturn(testHumanDog);

        HumanDog result = humanDogService.saveDog(testHumanDog);

        assertEquals(testHumanDog, result);

        verify(repository, times(1)).save(testHumanDog);
    }

    @Test
    void testExistsByChatId() {
        Long chatId = 123456789L;

        when(repository.existsByChatId(chatId)).thenReturn(true);

        boolean result = humanDogService.existsByChatId(chatId);

        assertTrue(result);
    }

    @Test
    void testFindAll() {
        HumanDog testHumanDog = new HumanDog();

        when(repository.findAll()).thenReturn(Collections.singletonList(testHumanDog));

        List<HumanDog> result = humanDogService.findAll();

        assertEquals(1, result.size());
        assertTrue(result.contains(testHumanDog));
    }
}
