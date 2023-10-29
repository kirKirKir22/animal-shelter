package pro.sky.telegrambotanimalshelter.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonDog extends Person {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public PersonDog(String name, int yearOfBirth, String phone, String address, Long chatId, Status status, Dog dog) {
        super(name, yearOfBirth, phone, address, chatId, status);
        this.dog = dog;
    }

    public PersonDog(String name, int yearOfBirth, String phone, String address, Long chatId, Status status) {
        super(name, yearOfBirth, phone, address, chatId, status);
    }

    public PersonDog(String name, String phone, Long chatId) {
        super(name, phone, chatId);
    }

    public PersonDog(Long chatId){
        super(chatId);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Report> reports;
}