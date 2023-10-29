package pro.sky.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.service.implementation.HumanCatServiceImpl;

import java.util.Collection;


@RestController
@RequestMapping("person-cat")
public class HumanCatController {

    private final HumanCatServiceImpl personCatService;

    public HumanCatController(HumanCatServiceImpl personCatService) {
        this.personCatService = personCatService;
    }

    @Operation(summary = "Получение пользователя, усыновителя кота,  по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, усыновитель кота, найден по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @GetMapping("/{id}")
    public HumanCat getById(@Parameter(description = "PersonCat id") @PathVariable Long id) {
        return this.personCatService.getByIdHumanCat(id);
    }

    @Operation(summary = "Создание пользователя, усыновителя кота",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Пользователь, усыновитель кота, создан",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HumanCat.class)
                    )
            ),
            tags = "PersonCat"
    )
    @PostMapping()
    public HumanCat save(@RequestBody HumanCat personCat) {
        return this.personCatService.addHumanCat(personCat);
    }

    @Operation(summary = "Изменение данных пользователя, усыновителя кота",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Данные пользователя, усыновителя кота, изменены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HumanCat.class)
                    )
            ),
            tags = "PersonCat"
    )
    @PutMapping
    public HumanCat update(@RequestBody HumanCat personCat) {
        return this.personCatService.addHumanCat(personCat);
    }

    @Operation(summary = "Удаление пользователя, усыновителя кота, по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, усыновитель кота, удален по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "PersonCat id")@PathVariable Long id) {
        this.personCatService.removeByIdHumanCat(id);
    }

    @Operation(summary = "Просмотр всех пользователей, усыновителей кота",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получены все пользователи, усыновители кота",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @GetMapping("/all")
    public Collection<HumanCat> getAll() {
        return this.personCatService.getAllHumanCat();
    }
}