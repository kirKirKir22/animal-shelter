package pro.sky.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.exceptions.CatNotFoundException;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.Collection;

@RestController
@RequestMapping("cat")
public class CatController {

    private final CatService catService;

    // Конструктор контроллера, который принимает сервис в качестве зависимости.
    public CatController(CatService catService) {
        this.catService = catService;
    }

    // Получение кота по ID
    @Operation(summary = "Получение кота по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Кот по ID найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @GetMapping("/{id}")
    public Cat getById(@Parameter(description = "ID кота") @PathVariable Long id) {
        return this.catService.getByIdCat(id);
    }

    // Создание нового кота
    @Operation(summary = "Создание нового кота",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Кот создан",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            tags = "Cat"
    )
    @PostMapping()
    public Long save(@RequestBody Cat cat) {
        return this.catService.addCat(cat).getId();
    }

    // Редактирование данных кота
    @Operation(summary = "Редактирование данных кота",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные кота отредактированы",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            tags = "Cat"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cat> update(@PathVariable Long id, @RequestBody Cat cat) {
        Cat existingCat = catService.getByIdCat(id);

        if (existingCat == null) {
            throw new CatNotFoundException();
        }

        Cat updatedCat = catService.updateCat(cat);

        return new ResponseEntity<>(updatedCat, HttpStatus.OK);
    }

    // Удаление кота по ID
    @Operation(summary = "Удаление кота по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Кот удален по ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "ID кота") @PathVariable Long id) {
        this.catService.removeByIdCat(id);
    }

    // Получение всех котов
    @Operation(summary = "Получение всех котов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список всех котов",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @GetMapping("/all")
    public Collection<Cat> getAll() {
        return this.catService.getAllCat();
    }
}
