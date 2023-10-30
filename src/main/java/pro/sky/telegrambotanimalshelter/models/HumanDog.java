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
public class HumanDog extends Human {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public HumanDog(String name, int yearOfBirth, String phone, String address, Long chatId, Status status, Dog dog) {
        super(name, yearOfBirth, phone, address, chatId, status);
        this.dog = dog;
    }

    public HumanDog(String name, int yearOfBirth, String phone, String address, Long chatId, Status status) {
        super(name, yearOfBirth, phone, address, chatId, status);
    }

    public HumanDog(String name, String phone, Long chatId) {
        super(name, phone, chatId);
    }

    public HumanDog(Long chatId){
        super(chatId);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private Dog dog;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Report> reports;
}