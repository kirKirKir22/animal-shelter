package pro.sky.telegrambotanimalshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatException {

    @ExceptionHandler(CatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCatNotFoundException(CatNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CatConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleCatConflictException(CatConflictException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class CatNotFoundException extends RuntimeException {
        public CatNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class CatConflictException extends RuntimeException {
        public CatConflictException(String message) {
            super(message);
        }
    }
}
