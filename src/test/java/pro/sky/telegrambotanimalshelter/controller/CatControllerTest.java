package pro.sky.telegrambotanimalshelter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatController.class)
public class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Test
    public void testGetById() throws Exception {
        given(catService.getByIdCat(1L)).willReturn(new Cat());

        mockMvc.perform(get("/cat/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testUpdate() throws Exception {
        given(catService.getByIdCat(1L)).willReturn(new Cat());
        given(catService.updateCat(any(Cat.class))).willReturn(new Cat());

        mockMvc.perform(put("/cat/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Whiskers\", \"age\": 3}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete("/cat/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        given(catService.getAllCat()).willReturn(Arrays.asList(new Cat(), new Cat()));

        mockMvc.perform(get("/cat/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
