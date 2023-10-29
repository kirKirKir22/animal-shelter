package pro.sky.telegrambotanimalshelter.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.controller.PersonDogController;
import pro.sky.telegrambotanimalshelter.models.PersonDog;
import pro.sky.telegrambotanimalshelter.service.implementation.PersonDogServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PersonDogController.class)
public class PersonDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDogServiceImpl personDogService;

    @Test
    void getByIdPersonDog() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);

        when(personDogService.getByIdPersonDog(anyLong())).thenReturn(personDog);

        mockMvc.perform(
                        get("/person-dog/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personDogService).getByIdPersonDog(1L);
    }

    @Test
    void addPersonDog() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);
        personDog.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(personDogService.addPersonDog(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        post("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personDogService).addPersonDog(personDog);
    }

    @Test
    void updatePersonDog() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);
        personDog.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(personDogService.updatePersonDog(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        put("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(personDogService).updatePersonDog(personDog);
    }

    @Test
    void deletePersonDog() throws Exception {
        mockMvc.perform(
                        delete("/person-dog/{id}", 1))
                .andExpect(status().isOk());
        verify(personDogService).removeByIdPersonDog(1L);
    }

    @Test
    void getAll() throws Exception {
        when(personDogService.getAllPersonDog()).thenReturn(List.of(new PersonDog()));

        mockMvc.perform(
                        get("/person-dog/all"))
                .andExpect(status().isOk());
    }
}
