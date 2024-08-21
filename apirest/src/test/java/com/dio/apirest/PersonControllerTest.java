/**
 * This file is the test class for the PersonController class in the apirest package.
 * It contains test cases for the methods in the PersonController class.
 */
package com.dio.apirest;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dio.apirest.controller.PersonController;
import com.dio.apirest.model.Person;
import com.dio.apirest.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private Person person;

    @BeforeEach
    public void setup() {
        person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        person.setAge(30);
    }

    @Test
    public void testGetAll() throws Exception {
        when(personService.findAll()).thenReturn(Arrays.asList(person));

        mockMvc.perform(get("/api/person"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetById() throws Exception {
        when(personService.findById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/api/person/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreate() throws Exception {
        when(personService.save(any(Person.class))).thenReturn(person); // Reflete a mudança de 'create' para 'save'

        mockMvc.perform(post("/api/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(person)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/person/1"))
            .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdate() throws Exception {
        when(personService.update(anyLong(), any(Person.class))).thenReturn(person);

        mockMvc.perform(put("/api/person/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(person)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testDelete() throws Exception {
        Long personId = 1L;

        doNothing().when(personService).delete(personId);

        mockMvc.perform(delete("/api/person/{id}", personId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        verify(personService).delete(personId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
