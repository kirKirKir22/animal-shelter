package pro.sky.telegrambotanimalshelter.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Animals {


    @Column(name = "name")
    private String name;


    @Column(name = "breed")
    private String breed;


    @Column(name = "age")
    private int age;


    @Column(name = "description")
    private String description;

}