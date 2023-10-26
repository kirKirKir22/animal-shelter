package pro.sky.telegrambotanimalshelter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class TelegramBotAnimalShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotAnimalShelterApplication.class, args);
	}

}
