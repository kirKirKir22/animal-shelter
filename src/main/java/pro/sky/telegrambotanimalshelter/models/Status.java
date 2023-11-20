package pro.sky.telegrambotanimalshelter.models;

public enum Status {
    APPROVED("Подтверждено"),
    REFUSED("Отказано"),
    TRIAL_PERIOD("Испытательный период"),
    SEARCH("В поиске");

    private String translate;

    Status(String translate) {
        this.translate = translate;
    }
}