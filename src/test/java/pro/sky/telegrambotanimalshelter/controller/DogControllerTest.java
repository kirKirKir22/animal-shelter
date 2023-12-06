package pro.sky.telegrambotanimalshelter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.models.Dog;
import pro.sky.telegrambotanimalshelter.service.implementation.DogServiceImpl;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DogController.class)
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogServiceImpl dogService;

    @Test
    public void testGetById() throws Exception {
        given(dogService.getByIdDog(anyLong())).willReturn(new Dog());

        mockMvc.perform(get("/dog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdate() throws Exception {
        given(dogService.getByIdDog(anyLong())).willReturn(new Dog());
        given(dogService.updateDog(any(Dog.class))).willReturn(new Dog());

        mockMvc.perform(put("/dog/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Rex\", \"age\": 6}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateNonExistingDog() throws Exception {
        given(dogService.getByIdDog(anyLong())).willReturn(null);

        mockMvc.perform(put("/dog/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Rex\", \"age\": 6}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete("/dog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        given(dogService.getAllDog()).willReturn(Arrays.asList(new Dog(), new Dog()));

        mockMvc.perform(get("/dog/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
