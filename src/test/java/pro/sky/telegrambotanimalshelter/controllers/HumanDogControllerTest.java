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
public class HumanDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HumanDogServiceImpl humanDogService;

    @Test
    void getByIdHumanDog() throws Exception {
        HumanDog humanDog = new HumanDog();
        humanDog.setId(1L);

        when(humanDogService.getByIdHumanDog(anyLong())).thenReturn(humanDog);

        mockMvc.perform(
                        get("/human-dog/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(humanDogService).getByIdHumanDog(1L);
    }

    @Test
    void addHumanDog() throws Exception {
        HumanDog humanDog = new HumanDog();
        humanDog.setId(1L);
        humanDog.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(humanDogService.addHumanDog(humanDog)).thenReturn(humanDog);

        mockMvc.perform(
                        post("/human-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(humanDogService).addHumanDog(humanDog);
    }

    @Test
    void updateHumanDog() throws Exception {
        HumanDog humanDog = new HumanDog();
        humanDog.setId(1L);
        humanDog.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(humanDogService.updateHumanDog(humanDog)).thenReturn(humanDog);

        mockMvc.perform(
                        put("/human-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(humanDogService).updateHumanDog(humanDog);
    }

    @Test
    void deleteHumanDog() throws Exception {
        mockMvc.perform(
                        delete("/human-dog/{id}", 1))
                .andExpect(status().isOk());
        verify(humanDogService).removeByIdHumanDog(1L);
    }

    @Test
    void getAll() throws Exception {
        when(humanDogService.getAllHumanDog()).thenReturn(List.of(new HumanDog()));

        mockMvc.perform(
                        get("/human-dog/all"))
                .andExpect(status().isOk());
    }
}
