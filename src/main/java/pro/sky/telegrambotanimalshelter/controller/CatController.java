package pro.sky.telegrambotanimalshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public Cat create(@RequestBody Cat cat) {
        return catService.add(cat);
    }

    @GetMapping("/{id}")
    public Cat read(@PathVariable long id) {
        return catService.read(id);
    }

    @PutMapping("/{id}")
    public Cat update(@PathVariable long id, @RequestBody Cat cat) {
        cat.setId(id);
        return catService.update(cat);
    }

    @DeleteMapping("/{id}")
    public Cat delete(@PathVariable long id) {
        return catService.delete(id);
    }

    @GetMapping("/all")
    public List<Cat> findAll() {
        return catService.findAll();
    }
}
