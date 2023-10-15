package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

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
    private List<DogModel> dogs;

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

    public List<DogModel> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogModel> dogs) {
        this.dogs = dogs;
    }
}
