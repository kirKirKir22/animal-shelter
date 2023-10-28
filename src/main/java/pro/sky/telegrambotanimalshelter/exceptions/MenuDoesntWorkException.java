package pro.sky.telegrambotanimalshelter.exceptions;

public class MenuDoesntWorkException extends RuntimeException{

    public MenuDoesntWorkException(String message) {
        super("Ошибка при вызове меню");
    }
}