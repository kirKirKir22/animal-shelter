package pro.sky.telegrambotanimalshelter.controllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.controller.CatController;
import pro.sky.telegrambotanimalshelter.models.Cat;
import pro.sky.telegrambotanimalshelter.service.interfaces.CatService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CatController.class)
class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;


    @Test
    void getByIdCat() throws Exception {
        Cat cat = new Cat();
        cat.setId(1L);

        when(catService.getByIdCat(anyLong())).thenReturn(cat);

        mockMvc.perform(
                        get("/cat/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        verify(catService).getByIdCat(1L);
    }


    @Test
    void addCat() throws Exception {
        Cat cat = new Cat();
        cat.setId(null);
        cat.setName("Кот");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Кот");

        when(catService.addCat(cat)).thenReturn(cat);

        mockMvc.perform(
                        post("/cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(catService).addCat(cat);
    }


    @Test
    void updateCat() throws Exception {
        Cat cat = new Cat();
        cat.setId(null);
        cat.setName("Кот");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Кот");

        when(catService.updateCat(cat)).thenReturn(cat);

        mockMvc.perform(
                        put("/cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(catService).updateCat(cat);
    }


    @Test
    void deleteByIdCat() throws Exception {
        mockMvc.perform(
                        delete("/cat/{id}", 1))
                .andExpect(status().isOk());
        verify(catService).removeByIdCat(1L);
    }


    @Test
    void getAllCat() throws Exception {
        when(catService.getAllCat()).thenReturn(List.of(new Cat()));

        mockMvc.perform(
                        get("/cat/all"))
                .andExpect(status().isOk());
    }
}
