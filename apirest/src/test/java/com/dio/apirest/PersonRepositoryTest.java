/**
 * This file represents the test class for the PersonRepository class in the apirest package.
 * It contains test cases for the methods implemented in the PersonRepository class.
 */
package com.dio.apirest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

/**
 * Test class for the PersonRepository.
 * 
 * This class uses the @DataJpaTest annotation to create a Spring Data JPA test context 
 * and configure the PersonRepository for testing. It uses the @Autowired annotation 
 * to inject the PersonRepository into the test class.
 * 
 * The setup method is annotated with @BeforeEach and is executed before each test method 
 * to prepare the test environment by creating a Person object with predefined values.
 * 
 * Each test method is annotated with @Test and follows the naming convention 
 * of "test<Operation>" to clearly indicate the purpose of the test.
 * 
 * The tests verify the functionality of the PersonRepository methods, including saving, 
 * finding by ID, and deleting persons.
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 * Since: 2023-08-23
 */
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    /**
     * Sets up the test environment by creating a Person object 
     * with predefined values. This method is executed before each test.
     */
    @BeforeEach
    public void setup() {
        person = new Person();
        person.setName("John Doe");
        person.setAge(30);
    }

    /**
     * Tests the save method of the PersonRepository.
     * 
     * This test verifies that a person can be saved using the PersonRepository.
     */
    @Test
    public void testSavePerson() {
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getId()).isNotNull();
    }

    /**
     * Tests the findById method of the PersonRepository.
     * 
     * This test verifies that a person can be found by ID using the PersonRepository.
     */
    @Test
    public void testFindById() {
        Person savedPerson = personRepository.save(person);
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());
        assertThat(foundPerson).isPresent();
        assertThat(foundPerson.get().getName()).isEqualTo("John Doe");
    }

    /**
     * Tests the deleteById method of the PersonRepository.
     * 
     * This test verifies that a person can be deleted by ID using the PersonRepository.
     */
    @Test
    public void testDeletePerson() {
        Person savedPerson = personRepository.save(person);
        personRepository.deleteById(savedPerson.getId());
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());
        assertThat(foundPerson).isNotPresent();
    }
}