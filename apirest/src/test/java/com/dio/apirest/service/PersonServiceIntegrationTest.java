package com.dio.apirest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dio.apirest.exception.EntityNotFoundException;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

/**
 * Integration test class for the PersonService.
 * 
 * This class tests the integration of the PersonService with the PersonRepository 
 * and verifies that the service methods work correctly with the database.
 * 
 * The tests include creating, finding, updating, and deleting persons, ensuring 
 * that the service behaves as expected in a real-world scenario.
 * 
 * Each test method is annotated with @Test and follows the naming convention 
 * of "test<Operation>" to clearly indicate the purpose of the test.
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 * Since: 2023-08-23
 */
@SpringBootTest
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Sets up the test environment by deleting all persons from the repository 
     * before each test. This ensures a clean state for each test execution.
     */
    @BeforeEach
    public void setUp() {
        personRepository.deleteAll();
    }

    /**
     * Tests the creation of a person using the PersonService.
     * 
     * This test verifies that a new person can be created and that the 
     * returned person has the expected properties.
     */
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

    /**
     * Tests finding a person by ID using the PersonService.
     * 
     * This test verifies that a person can be found by its ID and that 
     * the returned person's properties match the expected values.
     */
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

    /**
     * Tests updating a person's information using the PersonService.
     * 
     * This test verifies that an existing person's details can be updated 
     * and that the updated person has the expected properties.
     */
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

    /**
     * Tests deleting a person using the PersonService.
     * 
     * This test verifies that a person can be deleted by ID and that 
     * attempting to find the deleted person results in an EntityNotFoundException.
     */
    @Test
    public void testDeletePerson() {
        // Create and save a person in the repository
        Person person = new Person();
        person.setName("Jane Smith");
        person.setAge(35);
        person = personRepository.save(person);

        final Long personId = person.getId();
        
        // Delete the person
        personService.delete(personId);

        // Verify that the person was actually deleted and throws an exception when trying to find
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> personService.findById(personId));
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Person with id " + personId + " not found.");
    }
}