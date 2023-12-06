package pro.sky.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanCatService;

import java.util.Collection;

@RestController
@RequestMapping("human-cat")
public class HumanCatController {

    private final HumanCatService humanCatService;

    // Конструктор контроллера, который принимает сервис в качестве зависимости.
    public HumanCatController(HumanCatService humanCatService) {
        this.humanCatService = humanCatService;
    }

    // Получение пользователя, усыновителя кота, по ID
    @Operation(summary = "Получение пользователя, усыновителя кота, по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, усыновитель кота, найден по ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @GetMapping("/{id}")
    public HumanCat getById(@Parameter(description = "PersonCat ID") @PathVariable Long id) {
        return this.humanCatService.getByIdHumanCat(id);
    }

    // Создание пользователя, усыновителя кота
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
        return this.humanCatService.addHumanCat(personCat);
    }

    // Изменение данных пользователя, усыновителя кота
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
        return this.humanCatService.updateHumanCat(personCat);
    }

    // Удаление пользователя, усыновителя кота, по ID
    @Operation(summary = "Удаление пользователя, усыновителя кота, по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, усыновитель кота, удален по ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HumanCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "PersonCat ID")@PathVariable Long id) {
        this.humanCatService.removeByIdHumanCat(id);
    }

    // Просмотр всех пользователей, усыновителей кота
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
        return this.humanCatService.getAllHumanCat();
    }
}
