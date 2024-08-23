/**
 * This file represents the integration test for the PersonController class.
 * It is located at the following file path: 
 * /c:/Users/pedro/Documents/Pedro Solozabal/DIO/dio-apirest/apirest/src/test/java/com/dio/apirest/PersonControllerIntegrationTest.java
 * 
 * This test class is responsible for testing the integration of the PersonController class 
 * with the rest of the application. It ensures that the PersonController class functions 
 * correctly in a real-world scenario.
 */
package com.dio.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

/**
 * Integration test class for the PersonController.
 * 
 * This class contains tests to verify the functionality of the PersonController 
 * in a Spring Boot application. It includes tests for creating, retrieving, 
 * updating, and deleting Person entities.
 * 
 * The tests use MockMvc to simulate HTTP requests and validate the responses 
 * from the PersonController.
 * 
 * The tests are transactional, meaning that any changes made to the database 
 * during the tests will be rolled back after each test method is executed.
 * 
 * Each test method is annotated with @Test and follows the naming convention 
 * of "test<Functionality>" to clearly indicate the purpose of the test.
 * 
 * @see PersonController
 * @see Person
 * @see PersonRepository
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 * Since: 2023-08-23
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Sets up the test environment by deleting all persons from the repository 
     * before each test.
     */
    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
    }

    /**
     * Tests the creation of a person using the PersonController.
     * 
     * @throws Exception if any error occurs during the test execution.
     */
    @Test
    public void testCreatePerson() throws Exception {
        String personJson = "{\"name\":\"John Doe\",\"age\":30}";

        MvcResult result = mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJson))
                .andExpect(status().isCreated())
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        assertThat(location).isNotNull();

        Person person = personRepository.findAll().get(0);
        assertThat(person.getName()).isEqualTo("John Doe");
        assertThat(person.getAge()).isEqualTo(30);
    }

    /**
     * Tests retrieving a person by ID using the PersonController.
     * 
     * @throws Exception if any error occurs during the test execution.
     */
    @Test
    public void testGetPersonById() throws Exception {
        Person person = new Person();
        person.setName("Jane Doe");
        person.setAge(25);
        person = personRepository.save(person);

        mockMvc.perform(get("/api/person/" + person.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(25));
    }

    /**
     * Tests updating a person using the PersonController.
     * 
     * @throws Exception if any error occurs during the test execution.
     */
    @Test
    public void testUpdatePerson() throws Exception {
        Person person = new Person();
        person.setName("John Smith");
        person.setAge(40);
        person = personRepository.save(person);

        String updatedPersonJson = "{\"name\":\"John Smith Updated\",\"age\":45}";

        mockMvc.perform(put("/api/person/" + person.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPersonJson))
                .andExpect(status().isOk());

        Person updatedPerson = personRepository.findById(person.getId()).orElse(null);
        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.getName()).isEqualTo("John Smith Updated");
        assertThat(updatedPerson.getAge()).isEqualTo(45);
    }

    /**
     * Tests deleting a person using the PersonController.
     * 
     * @throws Exception if any error occurs during the test execution.
     */
    @Test
    public void testDeletePerson() throws Exception {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        person = personRepository.save(person);

        mockMvc.perform(delete("/api/person/" + person.getId()))
                .andExpect(status().isNoContent());

        assertThat(personRepository.findById(person.getId())).isEmpty();
    }
}