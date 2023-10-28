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

    public DogController(DogServiceImpl dogService) {
        this.dogService = dogService;
    }

    @Operation(summary = "Получение собаки по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака по id найдена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @GetMapping("/{id}")
    public Dog getById(@Parameter(description = "dog id") @PathVariable Long id) {
        return this.dogService.getByIdDog(id);
    }

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

    @Operation(summary = "Удаление собаки по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака удалена по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter (description = "dog id")@PathVariable Long id) {
        this.dogService.removeByIdDog(id);
    }

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