package pro.sky.telegrambotanimalshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserForCatsShelterException {

    @ExceptionHandler(UserForCatsShelterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserForCatsShelterNotFoundException(UserForCatsShelterNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UserForCatsShelterConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserForCatsShelterConflictException(UserForCatsShelterConflictException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class UserForCatsShelterNotFoundException extends RuntimeException {
        public UserForCatsShelterNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class UserForCatsShelterConflictException extends RuntimeException {
        public UserForCatsShelterConflictException(String message) {
            super(message);
        }
    }
}