package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class UserForDogsShelter {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String chatId;

    @OneToMany(mappedBy = "owner")
    private List<Dog> dogs;

    public UserForDogsShelter() {
    }

    public UserForDogsShelter(String name, String phone, String chatId) {
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

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForDogsShelter that = (UserForDogsShelter) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(chatId, that.chatId) && Objects.equals(dogs, that.dogs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, chatId, dogs);
    }

    @Override
    public String toString() {
        return "UserForDogsShelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", chatId='" + chatId + '\'' +
                ", dogs=" + dogs +
                '}';
    }
}
