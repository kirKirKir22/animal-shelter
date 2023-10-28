package pro.sky.telegrambotanimalshelter.models;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {


    private String name;


    private int yearOfBirth;


    private String phone;


    private String address;


    private Long chatId;


    @Enumerated(EnumType.STRING)
    private Status status;

    public Person(String name, int yearOfBirth, String phone, String address, Long chatId, Status status) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phone = phone;
        this.address = address;
        this.chatId = chatId;
        this.status = status;
    }

    public Person(String name, String phone, Long chatId) {
        this.name = name;
        this.phone = phone;
        this.chatId = chatId;
    }

    public Person(Long chatId){
        this.chatId = chatId;
    }
}