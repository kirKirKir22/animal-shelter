package pro.sky.telegrambotanimalshelter.models;

import com.pengrad.telegrambot.model.Update;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "telegram_users")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    private Long userId;
    private Update update;

}
