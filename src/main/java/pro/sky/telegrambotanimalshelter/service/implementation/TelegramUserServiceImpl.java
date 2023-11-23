package pro.sky.telegrambotanimalshelter.service.implementation;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.TelegramUser;
import pro.sky.telegrambotanimalshelter.repository.TelegramUserRepository;
import pro.sky.telegrambotanimalshelter.service.interfaces.TelegramUserService;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserServiceImpl(TelegramUserRepository updateRepository) {
        this.telegramUserRepository = updateRepository;
    }

    @Override
    public void updateTelegramUser(Long userId, Update newUpdate) {
        TelegramUser user = telegramUserRepository.findByUserId(userId).orElse(new TelegramUser());
        user.setUserId(userId);
        user.setUpdate(newUpdate);
        telegramUserRepository.save(user);
    }

    @Override
    public Update getTelegramUserUpdate(Long userId) {
        return telegramUserRepository.findById(userId)
                .map(TelegramUser::getUpdate)
                .orElse(null);
    }
}
