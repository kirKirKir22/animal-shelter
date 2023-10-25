package pro.sky.telegrambotanimalshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.service.interfaces.DogService;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public Dog create(@RequestBody Dog dog) {
        return dogService.add(dog);
    }

    @GetMapping("/{id}")
    public Dog read(@PathVariable long id) {
        return dogService.read(id);
    }

    @PutMapping("/{id}")
    public Dog update(@PathVariable long id, @RequestBody Dog dog) {
        dog.setId(id);
        return dogService.update(dog);
    }

    @DeleteMapping("/{id}")
    public Dog delete(@PathVariable long id) {
        return dogService.delete(id);
    }

    @GetMapping("/all")
    public List<Dog> findAll() {
        return dogService.findAll();
    }
}