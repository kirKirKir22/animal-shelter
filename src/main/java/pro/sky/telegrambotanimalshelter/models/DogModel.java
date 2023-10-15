package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DogModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String breed;

    @Column
    private int age;

    @ManyToOne
    private UserForDogsShelter owner;

    public DogModel() {
    }

    public DogModel(String name, String breed, int age, UserForDogsShelter owner) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserForDogsShelter getOwner() {
        return owner;
    }

    public void setOwner(UserForDogsShelter owner) {
        this.owner = owner;
    }
}
