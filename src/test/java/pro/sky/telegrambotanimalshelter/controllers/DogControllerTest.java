package pro.sky.telegrambotanimalshelter.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.controller.DogController;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.service.implementation.DogServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DogController.class)
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogServiceImpl dogService;


    @Test
    void getByIdDog() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);

        when(dogService.getByIdDog(anyLong())).thenReturn(dog);

        mockMvc.perform(
                        get("/dog/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        verify(dogService).getByIdDog(1L);
    }


    @Test
    void addDog() throws Exception {
        Dog dog = new Dog();
        dog.setId(null);
        dog.setName("Собака");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Собака");

        when(dogService.addDog(dog)).thenReturn(dog);

        mockMvc.perform(
                        post("/dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(dogService).addDog(dog);
    }


    @Test
    void updateDog() throws Exception {
        Dog dog = new Dog();
        dog.setId(null);
        dog.setName("Собака");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Собака");

        when(dogService.updateDog(dog)).thenReturn(dog);

        mockMvc.perform(
                        put("/dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(dogService).updateDog(dog);
    }


    @Test
    void deleteByIdDog() throws Exception {
        mockMvc.perform(
                        delete("/dog/{id}", 1))
                .andExpect(status().isOk());
        verify(dogService).removeByIdDog(1L);
    }


    @Test
    void getAllDog() throws Exception {
        when(dogService.getAllDog()).thenReturn(List.of(new Dog()));

        mockMvc.perform(
                        get("/dog/all"))
                .andExpect(status().isOk());
    }
}
