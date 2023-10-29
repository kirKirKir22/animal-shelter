package pro.sky.telegrambotanimalshelter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "cat")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cat extends Animals {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;


    public Cat(String name, String breed, int age, String description) {
        super(name, breed, age, description);
    }
}