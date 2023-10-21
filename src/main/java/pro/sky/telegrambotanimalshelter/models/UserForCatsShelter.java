package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import javax.persistence.OneToMany;

@Entity
public class UserForCatsShelter {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;  // Имя пользователя

    @Column(nullable = false)
    private String phone;  // Номер телефона пользователя

    @Column(nullable = false)
    private String chatId;  // Идентификатор чата пользователя в Telegram

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;  // Список котов, принадлежащих пользователю

    public UserForCatsShelter() {
        // Пустой конструктор
    }

    public UserForCatsShelter(String name, String phone, String chatId) {
        this.name = name;
        this.phone = phone;
        this.chatId = chatId;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForCatsShelter user = (UserForCatsShelter) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                phone.equals(user.phone) &&
                chatId.equals(user.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, chatId);
    }

    @Override
    public String toString() {
        return "UserForCatsShelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}