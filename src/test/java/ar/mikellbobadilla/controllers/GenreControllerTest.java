package ar.mikellbobadilla.controllers;

import ar.mikellbobadilla.dto.CreateGenre;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/genres")
                        .param("page", "1")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void testGetAllPageDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/genres")
                        .param("page", "2")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    void testCreateGenre() throws Exception {
        CreateGenre createGenre = new CreateGenre("acción");
        String requestBody = objectMapper.writeValueAsString(createGenre);

        mockMvc.perform(MockMvcRequestBuilders.post("/genres")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    void testBadCreateGenre() throws Exception {
        CreateGenre createGenre = new CreateGenre("1231!@##");
        String requestBody = objectMapper.writeValueAsString(createGenre);

        mockMvc.perform(MockMvcRequestBuilders.post("/genres")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/1")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/100")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }


    @Test
    void testUpdateGenreNotFound() throws Exception {
        CreateGenre createGenre = new CreateGenre("acción");
        String requestBody = objectMapper.writeValueAsString(createGenre);

        mockMvc.perform(MockMvcRequestBuilders.put("/genres/100")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    void testDeleteGenreNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/genres/100")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}