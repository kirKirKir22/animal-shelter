package pro.sky.telegrambotanimalshelter.service.implementation;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanCatService;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanDogService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

class ContactSharingServiceImplTest {
    @Test
    void shareContact_ShouldNotSaveCatAndDog_WhenContactIsNull() {
        // Mock dependencies
        HumanDogService humanDogService = Mockito.mock(HumanDogService.class);
        HumanCatService humanCatService = Mockito.mock(HumanCatService.class);
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);

        // Mock objects
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);

        // Set up mock behavior
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.contact()).thenReturn(null);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(chat.id()).thenReturn(123L);

        // Create ContactSharingServiceImpl instance
        ContactSharingServiceImpl contactSharingService = new ContactSharingServiceImpl(humanDogService, humanCatService, telegramBot);

        // Test the method
        contactSharingService.shareContact(update);

        // Verify interactions
        Mockito.verify(humanCatService, Mockito.never()).saveCat(any());
        Mockito.verify(humanDogService, Mockito.never()).saveDog(any());
        Mockito.verify(telegramBot, Mockito.never()).execute(any());
    }

    @Test
    void shareContact_ShouldSendForwardMessage_WhenLastNameIsNull() {
        // Mock dependencies
        HumanDogService humanDogService = Mockito.mock(HumanDogService.class);
        HumanCatService humanCatService = Mockito.mock(HumanCatService.class);
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);

        // Mock objects
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Contact contact = Mockito.mock(Contact.class);
        Chat chat = Mockito.mock(Chat.class);

        // Set up mock behavior
        Mockito.when(update.message()).thenReturn(message);
        Mockito.when(message.contact()).thenReturn(contact);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(chat.id()).thenReturn(123L);

        // Mock service behavior
        Mockito.when(humanDogService.existsByChatId(anyLong())).thenReturn(false);
        Mockito.when(humanCatService.existsByChatId(anyLong())).thenReturn(false);

        // Create ContactSharingServiceImpl instance
        ContactSharingServiceImpl contactSharingService = new ContactSharingServiceImpl(humanDogService, humanCatService, telegramBot);

        // Test the method
        contactSharingService.shareContact(update);

        // Verify interactions
        Mockito.verify(telegramBot, times(1)).execute(any(ForwardMessage.class));
    }

    // Add more tests to cover other scenarios
}
