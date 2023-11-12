package pro.sky.telegrambotanimalshelter.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "reports")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Report {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "chatId")
    private Long chatId;

    private String ration;

    private String health;

    private String habits;

    private Long days;

    @Column(name = "filePath")
    private String filePath;

    @Column(name = "fileSize")
    private Long fileSize;

    @Column(columnDefinition = "bytea")
    private byte[] data;

    private String caption;

    @Column(name = "lastMessage")
    private Date lastMessage;

    @Column(name = "lastMessageMs")
    private Long lastMessageMs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humanCat_id", referencedColumnName = "id")
    private HumanCat humanCat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "humanDog_id", referencedColumnName = "id")
    private HumanDog humanDog;



    public Report(Long chatId, String ration, String health, String habits, Long days, String filePath, Long fileSize,
                  byte[] data, String caption, Date lastMessage, Long lastMessageMs, HumanCat humanCat, HumanDog humanDog) {
        this.chatId = chatId;
        this.ration = ration;
        this.health = health;
        this.habits = habits;
        this.days = 0L;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.data = data;
        this.caption = caption;
        this.lastMessage = lastMessage;
        this.lastMessageMs = lastMessageMs;
        this.humanCat = humanCat;
        this.humanDog = humanDog;
    }


    public Report(Long chatId, String ration, String health, String habits, Long days, String caption) {
        this.chatId = chatId;
        this.ration = ration;
        this.health = health;
        this.habits = habits;
        this.days = days;
        this.caption = caption;
    }

    public Report(Long chatId){
        this.chatId = chatId;
        this.days = 0L;
    }
}