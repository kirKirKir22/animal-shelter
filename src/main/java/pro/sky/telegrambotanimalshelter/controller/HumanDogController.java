package pro.sky.telegrambotanimalshelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.service.implementation.HumanDogServiceImpl;

import java.util.Collection;


@RestController
@RequestMapping("human-dog")
public class HumanDogController {

    private final HumanDogServiceImpl humanDogService;

    public HumanDogController(HumanDogServiceImpl humanDogService) {
        this.humanDogService = humanDogService;
    }

    // Получение пользователя, усыновителя собаки, по id
    @Operation(summary = "Получение пользователя, усыновителя собаки,  по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, усыновитель собаки, найден по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanDog.class)
                            )
                    )
            },
            tags = "PersonDog"
    )
    @GetMapping("/{id}")
    public HumanDog getById(@Parameter(description = "PersonDog id") @PathVariable Long id) {
        return this.humanDogService.getByIdHumanDog(id);
    }

    // Создание пользователя, усыновителя собаки
    @Operation(summary = "Создание пользователя, усыновителя собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Пользователь, усыновитель собаки, найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HumanDog.class)
                    )
            ),
            tags = "PersonDog"
    )
    @PostMapping
    public HumanDog save(@RequestBody HumanDog personDog) {
        return this.humanDogService.addHumanDog(personDog);
    }

    // Изменение данных пользователя, усыновителя кота
    @Operation(summary = "Изменение данных пользователя, усыновителя собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Данные пользователя, усыновителя собаки, изменены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HumanDog.class)
                    )
            ),
            tags = "PersonDog"
    )
    @PutMapping
    public HumanDog update(@RequestBody HumanDog personDog) {
        return this.humanDogService.updateHumanDog(personDog);
    }

    // Удаление пользователей по id
    @Operation(summary = "Удаление пользователей по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, удаленный по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanDog.class)
                            )
                    )
            },
            tags = "PersonDog"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "PersonDog id") @PathVariable Long id) {
        this.humanDogService.removeByIdHumanDog(id);
    }

    // Просмотр всех пользователей
    @Operation(summary = "Просмотр всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все пользователи, либо определенные пользователи по chat_id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanDog.class)
                            )
                    )
            },
            tags = "PersonDog"
    )
    @GetMapping("/all")
    public Collection<HumanDog> getAll() {
        return this.humanDogService.getAllHumanDog();
    }
}
