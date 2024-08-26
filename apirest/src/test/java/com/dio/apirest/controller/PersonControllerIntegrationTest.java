package com.dio.apirest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import com.dio.apirest.config.TestSecurityConfig;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
    }

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