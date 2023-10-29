package pro.sky.telegrambotanimalshelter.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.controller.HumanDogController;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.service.implementation.HumanDogServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HumanDogController.class)
public class PersonDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HumanDogServiceImpl personDogService;

    @Test
    void getByIdPersonDog() throws Exception {
        HumanDog personDog = new HumanDog();
        personDog.setId(1L);

        when(personDogService.getByIdHumanDog(anyLong())).thenReturn(personDog);

        mockMvc.perform(
                        get("/person-dog/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personDogService).getByIdHumanDog(1L);
    }

    @Test
    void addPersonDog() throws Exception {
        HumanDog personDog = new HumanDog();
        personDog.setId(1L);
        personDog.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(personDogService.addHumanDog(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        post("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personDogService).addHumanDog(personDog);
    }

    @Test
    void updatePersonDog() throws Exception {
        HumanDog personDog = new HumanDog();
        personDog.setId(1L);
        personDog.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(personDogService.updateHumanDog(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        put("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personDogService).updateHumanDog(personDog);
    }

    @Test
    void deletePersonDog() throws Exception {
        mockMvc.perform(
                        delete("/person-dog/{id}", 1))
                .andExpect(status().isOk());
        verify(personDogService).removeByIdHumanDog(1L);
    }

    @Test
    void getAll() throws Exception {
        when(personDogService.getAllHumanDog()).thenReturn(List.of(new HumanDog()));

        mockMvc.perform(
                        get("/person-dog/all"))
                .andExpect(status().isOk());
    }
}
