package com.dio.apirest.controller;

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

import com.dio.apirest.model.Person;
import com.dio.apirest.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for the PersonController.
 * 
 * This class uses the WebMvcTest annotation to create a Spring MVC test context 
 * and configure the PersonController for testing. It uses the MockMvc class to 
 * simulate HTTP requests and validate the responses from the controller.
 * 
 * The PersonService is mocked using the @MockBean annotation, allowing for 
 * controlled responses from the service layer during the tests.
 * 
 * Each test method is annotated with @Test and follows the naming convention 
 * of "test<Operation>" to clearly indicate the purpose of the test.
 * 
 * The setup method is annotated with @BeforeEach and is executed before each 
 * test method to prepare the test environment.
 * 
 * The asJsonString method is a utility method used to convert an object to a 
 * JSON string for request content.
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 * Since: 2023-08-23
 */
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private Person person;

    /**
     * Sets up the test environment by creating a Person object 
     * with predefined values. This method is executed before each test.
     */
    @BeforeEach
    public void setup() {
        person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        person.setAge(30);
    }

    /**
     * Tests the GET all persons endpoint.
     * 
     * This test verifies that the PersonController returns a list of persons 
     * when the GET /api/person endpoint is requested.
     * 
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    public void testGetAll() throws Exception {
        when(personService.findAll()).thenReturn(Arrays.asList(person));

        mockMvc.perform(get("/api/person"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    /**
     * Tests the GET person by ID endpoint.
     * 
     * This test verifies that the PersonController returns a person 
     * when the GET /api/person/{id} endpoint is requested with a valid ID.
     * 
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    public void testGetById() throws Exception {
        when(personService.findById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/api/person/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"));
    }

    /**
     * Tests the POST create person endpoint.
     * 
     * This test verifies that the PersonController creates a person 
     * when the POST /api/person endpoint is requested with valid person data.
     * 
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    public void testCreate() throws Exception {
        when(personService.save(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/api/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(person)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/person/1"))
            .andExpect(jsonPath("$.name").value("John Doe"));
    }

    /**
     * Tests the PUT update person endpoint.
     * 
     * This test verifies that the PersonController updates a person 
     * when the PUT /api/person/{id} endpoint is requested with valid person data.
     * 
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    public void testUpdate() throws Exception {
        when(personService.update(anyLong(), any(Person.class))).thenReturn(person);

        mockMvc.perform(put("/api/person/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(person)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"));
    }

    /**
     * Tests the DELETE person endpoint.
     * 
     * This test verifies that the PersonController deletes a person 
     * when the DELETE /api/person/{id} endpoint is requested with a valid ID.
     * 
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    public void testDelete() throws Exception {
        Long personId = 1L;

        doNothing().when(personService).delete(personId);

        mockMvc.perform(delete("/api/person/{id}", personId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        verify(personService).delete(personId);
    }

    /**
     * Utility method to convert an object to a JSON string.
     * 
     * @param obj the object to be converted to JSON
     * @return the JSON string representation of the object
     */
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}