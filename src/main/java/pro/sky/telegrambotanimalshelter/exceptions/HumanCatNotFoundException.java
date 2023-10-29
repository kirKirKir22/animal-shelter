package pro.sky.telegrambotanimalshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class HumanCatNotFoundException extends RuntimeException {

    public HumanCatNotFoundException() {
        super("Усыновитель кота не найден!");
    }
}
