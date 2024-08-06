package com.example.demo;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void testAddItem() throws Exception {
        Item item = new Item(4, "Item4", "Item4 is here");

        when(itemService.addItem(any(Item.class))).thenReturn(item);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 4, \"name\": \"Item4\", \"description\": \"Item4 is here\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("Item4"))
                .andExpect(jsonPath("$.description").value("Item4 is here"));
    }

    @Test
    public void testGetItemById() throws Exception {
        Item item = new Item(1, "Item1", "Item1 is here");

        when(itemService.getItemById(1)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Item1"))
                .andExpect(jsonPath("$.description").value("Item1 is here"));
    }

    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = new Item(1, "Item1", "Item1 is here");
        Item item2 = new Item(2, "Item2", "Item2 is here");
        when(itemService.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Item1"))
                .andExpect(jsonPath("$[0].description").value("Item1 is here"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Item2"))
                .andExpect(jsonPath("$[1].description").value("Item2 is here"));
    }

    @Test
    public void testDeleteItemById() throws Exception {
        when(itemService.deleteItemById(1)).thenReturn(true);

        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isNoContent());
    }


}
