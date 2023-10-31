package pro.sky.telegrambotanimalshelter.exceptions;

public class MenuDoesNotWorkException extends RuntimeException{

    public MenuDoesNotWorkException(String message) {
        super("Ошибка при вызове меню");
    }
}