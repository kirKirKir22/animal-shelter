package pro.sky.telegrambotanimalshelter.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class HumanCat extends Human {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public HumanCat(String name, int yearOfBirth, String phone, String address, Long chatId, Status status, Cat cat) {
        super(name, yearOfBirth, phone, address, chatId, status);
        this.cat = cat;
    }

    public HumanCat(String name, int yearOfBirth, String phone, String address, Long chatId, Status status) {
        super(name, yearOfBirth, phone, address, chatId, status);
    }

    public HumanCat(String name, String phone, Long chatId) {
        super(name, phone, chatId);
    }

    public HumanCat(Long chatId){
        super(chatId);
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Cat cat;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Report> reports;

}