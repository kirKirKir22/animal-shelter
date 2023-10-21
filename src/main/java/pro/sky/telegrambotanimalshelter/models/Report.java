package pro.sky.telegrambotanimalshelter.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String animalName; // Имя животного

    @Column
    private String diet; // Рацион животного

    @Column
    private String wellBeing; // Общее самочувствие и привыкание к новому месту

    @Column
    private String behaviorChanges; // Изменение в поведении: отказ от старых привычек, приобретение новых

    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate; // Дата создания отчета

    @ManyToOne
    private UserForCatsShelter userForCats; // Ссылка на пользователя, выбравшего кошек

    @ManyToOne
    private UserForDogsShelter userForDogs; // Ссылка на пользователя, выбравшего собак

    @Column
    private String animalType; // Тип животного: "Cat" или "Dog"

    @Column
    private String filePath; // Путь к файлу фотографии животного

    @Column
    private Long fileSize; // Размер файла фотографии животного

    public Report(String animalName, Long id, String diet, String wellBeing, String behaviorChanges,
                  Date reportDate, UserForCatsShelter userForCats, UserForDogsShelter userForDogs,
                  String animalType, String filePath, Long fileSize) {
        this.animalName = animalName;
        this.id = id;
        this.diet = diet;
        this.wellBeing = wellBeing;
        this.behaviorChanges = behaviorChanges;
        this.reportDate = reportDate;
        this.userForCats = userForCats;
        this.userForDogs = userForDogs;
        this.animalType = animalType;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public Report() {
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWellBeing() {
        return wellBeing;
    }

    public void setWellBeing(String wellBeing) {
        this.wellBeing = wellBeing;
    }

    public String getBehaviorChanges() {
        return behaviorChanges;
    }

    public void setBehaviorChanges(String behaviorChanges) {
        this.behaviorChanges = behaviorChanges;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public UserForCatsShelter getUserForCats() {
        return userForCats;
    }

    public void setUserForCats(UserForCatsShelter userForCats) {
        this.userForCats = userForCats;
    }

    public UserForDogsShelter getUserForDogs() {
        return userForDogs;
    }

    public void setUserForDogs(UserForDogsShelter userForDogs) {
        this.userForDogs = userForDogs;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(animalName, report.animalName) && Objects.equals(diet, report.diet) && Objects.equals(wellBeing, report.wellBeing) && Objects.equals(behaviorChanges, report.behaviorChanges) && Objects.equals(reportDate, report.reportDate) && Objects.equals(userForCats, report.userForCats) && Objects.equals(userForDogs, report.userForDogs) && Objects.equals(animalType, report.animalType) && Objects.equals(filePath, report.filePath) && Objects.equals(fileSize, report.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalName, diet, wellBeing, behaviorChanges, reportDate, userForCats, userForDogs, animalType, filePath, fileSize);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", animalName='" + animalName + '\'' +
                ", diet='" + diet + '\'' +
                ", wellBeing='" + wellBeing + '\'' +
                ", behaviorChanges='" + behaviorChanges + '\'' +
                ", reportDate=" + reportDate +
                ", userForCats=" + userForCats +
                ", userForDogs=" + userForDogs +
                ", animalType='" + animalType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}