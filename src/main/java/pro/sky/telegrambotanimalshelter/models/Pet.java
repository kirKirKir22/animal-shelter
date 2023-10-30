package pro.sky.telegrambotanimalshelter.models;

public abstract class Pet {

    private String name;
    private String breed;
    private int age;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet() {
    }

    public Pet(String name, String breed, int age, String description) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
