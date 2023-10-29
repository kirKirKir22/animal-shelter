package pro.sky.telegrambotanimalshelter.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "person_cat")
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class PersonCat extends Person {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public PersonCat(String name, int yearOfBirth, String phone, String address, Long chatId, Status status, Cat cat) {
        super(name, yearOfBirth, phone, address, chatId, status);
        this.cat = cat;
    }

    public PersonCat(String name, int yearOfBirth, String phone, String address, Long chatId, Status status) {
        super(name, yearOfBirth, phone, address, chatId, status);
    }

    public PersonCat(String name, String phone, Long chatId) {
        super(name, phone, chatId);
    }

    public  PersonCat(Long chatId){
        super(chatId);
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Cat cat;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Report> reports;

}