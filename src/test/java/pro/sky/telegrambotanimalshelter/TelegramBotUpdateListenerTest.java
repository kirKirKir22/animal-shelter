package pro.sky.telegrambotanimalshelter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pro.sky.telegrambotanimalshelter.handlers.ReportHandler;
import pro.sky.telegrambotanimalshelter.keyboard.HotkeysShelter;
import pro.sky.telegrambotanimalshelter.listener.TelegramBotUpdateListener;
import pro.sky.telegrambotanimalshelter.service.interfaces.ContactSharingService;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanCatService;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanDogService;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TelegramBotUpdateListenerTest {

	@Mock
	private ReportService reportService;

	@Mock
	private HumanDogService humanDogService;

	@Mock
	private HumanCatService humanCatService;

	@Mock
	private HotkeysShelter keyBoardShelter;

	@Mock
	private TelegramBot telegramBot;

	@Mock
	private ReportHandler reportHandler;

	@Mock
	private ContactSharingService contactSharingService;

	@Mock
	private Update update;

	@InjectMocks
	private TelegramBotUpdateListener botUpdateListener;
	@InjectMocks
	private TelegramBotUpdateListener updateListener;

	@Test
	public void testProcess_handlesUpdate() {
		// Устанавливаем тестовые данные
		when(update.message()).thenReturn(mock(Message.class));
		when(update.message().chat()).thenReturn(mock(Chat.class));
		when(update.message().chat().id()).thenReturn(123456L);

		// Вызываем метод обработки
		botUpdateListener.process(Collections.singletonList(update));

		// Проверяем, что произошла обработка обновления
		// Например, можно проверить вызов нужных методов для обработки обновления
		verify(keyBoardShelter).chooseMenu(123456L);
		verify(telegramBot).execute(any(SendMessage.class));
	}

	@Test
	public void testScheduledMethod_lastUpdateNotNull_sendsMessage() {
		// Устанавливаем lastUpdate
		when(update.message()).thenReturn(mock(Message.class));
		when(update.message().chat()).thenReturn(mock(Chat.class));
		when(update.message().chat().id()).thenReturn(123456L);
		botUpdateListener.process(Collections.singletonList(update));

		// Вызываем метод по расписанию
		botUpdateListener.scheduledMethod();

		// Проверяем, что произошла отправка сообщения
		verify(telegramBot).execute(any(SendMessage.class));
	}

	@Test
	public void testScheduledMethod_lastUpdateNull_noAction() {
		// Вызываем метод по расписанию при отсутствии обновлений
		botUpdateListener.scheduledMethod();

		// Проверяем, что не произошло действий, если lastUpdate null
		verify(telegramBot, never()).execute(any(SendMessage.class));
	}

	@Test
	public void process_Test() {
		// Arrange
		Update update = mock(Update.class);
		Message message = mock(Message.class);
		Chat chat = mock(Chat.class);

		when(update.message()).thenReturn(message);
		when(message.chat()).thenReturn(chat);
		when(message.text()).thenReturn("Test message");
		when(chat.id()).thenReturn(12345L);

		List<Update> updates = Collections.singletonList(update);

		// Act
		updateListener.process(updates);
	}
}
