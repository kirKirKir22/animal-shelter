package pro.sky.telegrambotanimalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.telegrambotanimalshelter.exceptions.MenuDoesNotWorkException;
import pro.sky.telegrambotanimalshelter.keyboard.HotkeysShelter;
import pro.sky.telegrambotanimalshelter.models.*;
import pro.sky.telegrambotanimalshelter.repository.HumanCatRepository;
import pro.sky.telegrambotanimalshelter.repository.HumanDogRepository;
import pro.sky.telegrambotanimalshelter.repository.ReportRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TelegramBotUpdateListener.class})
@ExtendWith(SpringExtension.class)
class TelegramBotUpdateListenerTest {
    @MockBean
    private HotkeysShelter hotkeysShelter;

    @MockBean
    private HumanCatRepository humanCatRepository;

    @MockBean
    private HumanDogRepository humanDogRepository;

    @MockBean
    private ReportRepository reportRepository;

    @MockBean
    private ReportService reportService;

    @MockBean
    private TelegramBot telegramBot;

    @Autowired
    private TelegramBotUpdateListener telegramBotUpdateListener;

    private Dog dog;
    private Dog dog2;
    private HumanDog humanDog1;
    private HumanDog humanDog2;
    private Cat cat;
    private Cat cat2;
    private HumanCat humanCat1;
    private HumanCat humanCat2;



    @BeforeEach
    void setUp(){
        dog = new Dog();
        dog.setAge(1);
        dog.setBreed("Breed");
        dog.setDescription("Description");
        dog.setId(1L);
        dog.setName("Name");

        humanDog1 = new HumanDog();
        humanDog1.setAddress("Moscow");
        humanDog1.setChatId(1L);
        humanDog1.setDog(dog);
        humanDog1.setId(1L);
        humanDog1.setName("Name");
        humanDog1.setPhone("+79990001122");
        humanDog1.setReports(new ArrayList<>());
        humanDog1.setStatus(Status.APPROVED);
        humanDog1.setYearOfBirth(1);


        dog2 = new Dog();
        dog2.setAge(1);
        dog2.setBreed("Breed");
        dog2.setDescription("Description");
        dog2.setId(2L);
        dog2.setName("Name");

        humanDog2 = new HumanDog();
        humanDog2.setAddress("Moscow");
        humanDog2.setChatId(2L);
        humanDog2.setDog(dog2);
        humanDog2.setId(2L);
        humanDog2.setName("Name");
        humanDog2.setPhone("+79990001122");
        humanDog2.setReports(new ArrayList<>());
        humanDog2.setStatus(Status.REFUSED);
        humanDog2.setYearOfBirth(2000);

        cat = new Cat();
        cat.setAge(1);
        cat.setBreed("Breed");
        cat.setDescription("Description");
        cat.setId(1L);
        cat.setName("Name");

        humanCat1 = new HumanCat();
        humanCat1.setAddress("Moscow");
        humanCat1.setCat(cat);
        humanCat1.setChatId(1L);
        humanCat1.setId(1L);
        humanCat1.setName("Name");
        humanCat1.setPhone("+79990001122");
        humanCat1.setReports(new ArrayList<>());
        humanCat1.setStatus(Status.APPROVED);
        humanCat1.setYearOfBirth(1);

        cat2 = new Cat();
        cat2.setAge(1);
        cat2.setBreed("Breed");
        cat2.setDescription("Description");
        cat2.setId(2L);
        cat2.setName("Name");

        humanCat2 = new HumanCat();
        humanCat2.setAddress("Moscow");
        humanCat2.setCat(cat2);
        humanCat2.setChatId(2L);
        humanCat2.setId(2L);
        humanCat2.setName("Name");
        humanCat2.setPhone("+79990001122");
        humanCat2.setReports(new ArrayList<>());
        humanCat2.setStatus(Status.REFUSED);
        humanCat2.setYearOfBirth(2000);
    }

    @Test
    void shouldInitNotError() {
        doNothing().when(telegramBot).setUpdatesListener(Mockito.<UpdatesListener>any());
        telegramBotUpdateListener.init();
        verify(telegramBot).setUpdatesListener(Mockito.<UpdatesListener>any());
    }

    @Test
    void shouldExceptionInit() {
        doThrow(new RuntimeException("Error")).when(telegramBot).setUpdatesListener(Mockito.<UpdatesListener>any());
        assertThrows(RuntimeException.class, () -> telegramBotUpdateListener.init());
        verify(telegramBot).setUpdatesListener(Mockito.<UpdatesListener>any());
    }

    @Test
    void shouldAddUpdates() {
        ArrayList<Update> updates = new ArrayList<>();
        updates.add(new Update());
        updates.add(new Update());
        assertEquals(-1, telegramBotUpdateListener.process(updates));
    }

    @Test
    void shouldExceptionWhenAddUpdate() {
        Message message = mock(Message.class);
        when(message.text()).thenThrow(new MenuDoesNotWorkException("Don't send menu"));
        when(message.chat()).thenReturn(new Chat());
        Update update = mock(Update.class);
        when(update.message()).thenReturn(message);

        ArrayList<Update> updates = new ArrayList<>();
        updates.add(update);
        assertThrows(MenuDoesNotWorkException.class, () -> telegramBotUpdateListener.process(updates));
        verify(update, atLeast(1)).message();
        verify(message).chat();
        verify(message).text();
    }

    @Test
    void shouldSendReplyMessage() {
        when(telegramBot.execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any())).thenReturn(null);
        telegramBotUpdateListener.sendReplyMessage(1L, "Message Text", 1);
        verify(telegramBot).execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any());
    }

    @Test
    void sendReplyMessage() {
        when(telegramBot.execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any()))
                .thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> telegramBotUpdateListener
                .sendReplyMessage(1L, "Message Text", 1));
        verify(telegramBot).execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any());
    }

    @Test
    void shouldSendMessage() {
        when(telegramBot.execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any())).thenReturn(null);
        telegramBotUpdateListener.sendMessage(1L, "Text");
        verify(telegramBot).execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any());
    }

    @Test
    void shouldExceptionWhenSendMessage() {
        when(telegramBot.execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any()))
                .thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> telegramBotUpdateListener.sendMessage(1L, "Text"));
        verify(telegramBot).execute(Mockito.<BaseRequest<SendMessage, SendResponse>>any());
    }

    @Test
    void shouldShareContact() {
        Update update = mock(Update.class);
        when(update.message()).thenReturn(new Message());
        telegramBotUpdateListener.shareContact(update);
        verify(update).message();
    }

    @Test
    void shouldShareContactAndFindAllPerson() {
        when(humanDogRepository.findAll()).thenReturn(new ArrayList<>());
        when(humanCatRepository.findAll()).thenReturn(new ArrayList<>());
        when(telegramBot.execute(Mockito.<BaseRequest<DeleteMyCommands, BaseResponse>>any())).thenReturn(null);
        Message message = mock(Message.class);
        when(message.messageId()).thenReturn(1);
        when(message.chat()).thenReturn(new Chat());
        when(message.contact()).thenReturn(new Contact());
        Update update = mock(Update.class);
        when(update.message()).thenReturn(message);
        telegramBotUpdateListener.shareContact(update);
        verify(humanDogRepository).findAll();
        verify(humanCatRepository).findAll();
        verify(telegramBot, atLeast(1)).execute(Mockito.<BaseRequest<DeleteMyCommands, BaseResponse>>any());
        verify(update, atLeast(1)).message();
        verify(message, atLeast(1)).chat();
        verify(message, atLeast(1)).contact();
        verify(message).messageId();
    }

}