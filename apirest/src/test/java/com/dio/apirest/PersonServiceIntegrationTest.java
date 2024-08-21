/**
 * This file represents the integration test for the PersonService class.
 * It is located at the following file path: /c:/Users/pedro/Documents/Pedro Solozabal/DIO/dio-apirest/apirest/src/test/java/com/dio/apirest/PersonServiceIntegrationTest.java
 * 
 * The PersonServiceIntegrationTest class is responsible for testing the integration of the PersonService class with other components of the application.
 * It ensures that the PersonService class functions correctly in a real-world scenario, by testing its interactions with the database and other external dependencies.
 * 
 * This integration test class should be used to verify the overall behavior and functionality of the PersonService class, rather than testing individual methods in isolation.
 * It should cover various scenarios and edge cases to ensure the robustness and reliability of the PersonService class.
 */
package com.dio.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dio.apirest.exception.EntityNotFoundException;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;
import com.dio.apirest.service.PersonService;

@SpringBootTest
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    public void testCreatePerson() {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);

        Person createdPerson = personService.create(person);

        assertThat(createdPerson).isNotNull();
        assertThat(createdPerson.getId()).isNotNull();
        assertThat(createdPerson.getName()).isEqualTo("John Doe");
        assertThat(createdPerson.getAge()).isEqualTo(30);
    }

    @Test
    public void testFindPersonById() {
        Person person = new Person();
        person.setName("Jane Doe");
        person.setAge(25);
        person = personRepository.save(person);

        Person foundPerson = personService.findById(person.getId());

        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.getId()).isEqualTo(person.getId());
        assertThat(foundPerson.getName()).isEqualTo("Jane Doe");
        assertThat(foundPerson.getAge()).isEqualTo(25);
    }

    @Test
    public void testUpdatePerson() {
        Person person = new Person();
        person.setName("John Smith");
        person.setAge(40);
        person = personRepository.save(person);

        person.setName("John Smith Updated");
        person.setAge(45);

        Person updatedPerson = personService.update(person.getId(), person);

        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.getName()).isEqualTo("John Smith Updated");
        assertThat(updatedPerson.getAge()).isEqualTo(45);
    }

    @Test
public void testDeletePerson() {
    // Cria e salva uma pessoa no repositório
    Person person = new Person();
    person.setName("Jane Smith");
    person.setAge(35);
    person = personRepository.save(person);

    final Long personId = person.getId();
    
    // Exclui a pessoa
    personService.delete(personId);

    // Verifica se a pessoa foi realmente deletada e lança a exceção ao tentar buscar
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> personService.findById(personId));
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("Person with id " + personId + " not found.");
    }

}