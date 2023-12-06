package pro.sky.telegrambotanimalshelter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.service.interfaces.HumanCatService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HumanCatController.class)
public class HumanCatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HumanCatService humanCatService;

    @Test
    public void testGetById() throws Exception {
        given(humanCatService.getByIdHumanCat(1L)).willReturn(new HumanCat());

        mockMvc.perform(get("/human-cat/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSave() throws Exception {
        given(humanCatService.addHumanCat(any(HumanCat.class))).willReturn(new HumanCat());

        mockMvc.perform(post("/human-cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Alice\", \"age\": 25}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdate() throws Exception {
        given(humanCatService.updateHumanCat(any(HumanCat.class))).willReturn(new HumanCat());

        mockMvc.perform(put("/human-cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Bob\", \"age\": 30}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete("/human-cat/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        given(humanCatService.getAllHumanCat()).willReturn(Arrays.asList(new HumanCat(), new HumanCat()));

        mockMvc.perform(get("/human-cat/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
