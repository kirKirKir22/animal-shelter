package pro.sky.telegrambotanimalshelter.service.interfaces;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public interface TelegramUserService {

    void updateTelegramUser(Long userId, Update newUpdate);

    Update getTelegramUserUpdate(Long userId);
}
