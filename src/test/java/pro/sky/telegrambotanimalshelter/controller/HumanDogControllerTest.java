package pro.sky.telegrambotanimalshelter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.models.HumanDog;
import pro.sky.telegrambotanimalshelter.service.implementation.HumanDogServiceImpl;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class HumanDogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HumanDogServiceImpl humanDogService;

    @Test
    public void testGetById() throws Exception {
        given(humanDogService.getByIdHumanDog(1L)).willReturn(new HumanDog());

        mockMvc.perform(get("/human-dog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSave() throws Exception {
        given(humanDogService.addHumanDog(any(HumanDog.class))).willReturn(new HumanDog());

        mockMvc.perform(post("/human-dog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John\", \"age\": 30}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdate() throws Exception {
        given(humanDogService.updateHumanDog(any(HumanDog.class))).willReturn(new HumanDog());

        mockMvc.perform(put("/human-dog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John\", \"age\": 30}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete("/human-dog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        given(humanDogService.getAllHumanDog()).willReturn(Arrays.asList(new HumanDog(), new HumanDog()));

        mockMvc.perform(get("/human-dog/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
