package pro.sky.telegrambotanimalshelter.controllers;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotanimalshelter.controller.HumanCatController;
import pro.sky.telegrambotanimalshelter.models.HumanCat;
import pro.sky.telegrambotanimalshelter.service.implementation.HumanCatServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HumanCatController.class)
class HumanCatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HumanCatServiceImpl humanCatService;


    @Test
    void getByIdHumanCat() throws Exception {
        HumanCat humanCat = new HumanCat();
        humanCat.setId(1L);

        when(humanCatService.getByIdHumanCat(anyLong())).thenReturn(humanCat);

        mockMvc.perform(
                        get("/human-cat/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(humanCatService).getByIdHumanCat(1L);
    }


    @Test
    void addHumanCat() throws Exception {
        HumanCat humanCat = new HumanCat();
        humanCat.setId(1L);
        humanCat.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(humanCatService.addHumanCat(humanCat)).thenReturn(humanCat);

        mockMvc.perform(
                        post("/human-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(humanCatService).addHumanCat(humanCat);
    }


    @Test
    void updateHumanCat() throws Exception {
        HumanCat humanCatCat = new HumanCat();
        humanCatCat.setId(1L);
        humanCatCat.setName("Человек");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "Человек");

        when(humanCatService.addHumanCat(humanCatCat)).thenReturn(humanCatCat);

        mockMvc.perform(
                        put("/human-cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(humanCatService).addHumanCat(humanCatCat);
    }


    @Test
    void deleteHumanCat() throws Exception {
        mockMvc.perform(
                        delete("/human-cat/{id}", 1))
                .andExpect(status().isOk());
        verify(humanCatService).removeByIdHumanCat(1L);
    }


    @Test
    void getAllHumanCat() throws Exception {
        when(humanCatService.getAllHumanCat()).thenReturn(List.of(new HumanCat()));

        mockMvc.perform(
                        get("/human-cat/all"))
                .andExpect(status().isOk());
    }
}