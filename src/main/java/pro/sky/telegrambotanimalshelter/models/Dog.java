package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Dog {
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

    public Dog() {
    }

    public Dog(String name, String breed, int age, UserForDogsShelter owner) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age && Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(breed, dog.breed) && Objects.equals(owner, dog.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, owner);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", owner=" + owner +
                '}';
    }
}
