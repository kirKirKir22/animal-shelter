package pro.sky.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.service.implementation.DogServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("dog")
public class DogController {

    private final DogServiceImpl dogService;

    // Конструктор контроллера, который принимает сервис в качестве зависимости.
    public DogController(DogServiceImpl dogService) {
        this.dogService = dogService;
    }

    // Получение собаки по ID
    @Operation(summary = "Получение собаки по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака по ID найдена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @GetMapping("/{id}")
    public Dog getById(@Parameter(description = "ID собаки") @PathVariable Long id) {
        return this.dogService.getByIdDog(id);
    }

    // Создание новой собаки
    @Operation(summary = "Создание новой собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Собака создана",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Dog.class)
                    )
            ),
            tags = "Dog"
    )
    @PostMapping()
    public Dog save(@RequestBody Dog dog) {
        return this.dogService.addDog(dog);
    }

    // Редактирование данных собаки
    @Operation(summary = "Редактирование данных собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Данные собаки отредактированы",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Dog.class)
                    )
            ),
            tags = "Dog"
    )
    @PutMapping()
    public Dog update(@RequestBody Dog dog) {
        return this.dogService.updateDog(dog);
    }

    // Удаление собаки по ID
    @Operation(summary = "Удаление собаки по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака удалена по ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter (description = "ID собаки")@PathVariable Long id) {
        this.dogService.removeByIdDog(id);
    }

    // Просмотр всех собак
    @Operation(summary = "Просмотр всех собак",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список всех собак",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @GetMapping("/all")
    public Collection<Dog> getAll() {
        return this.dogService.getAllDog();
    }
}