package com.assignment.books.assignmentBooks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/book"))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void testGetEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book?author=sam&published=true"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
