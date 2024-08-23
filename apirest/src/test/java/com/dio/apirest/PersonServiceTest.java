/**
 * This file is the test class for the PersonService class in the apirest package.
 * It contains test cases for the methods in the PersonService class.
 * The PersonServiceTest class is located at the following file path:
 * /c:/Users/pedro/Documents/Pedro Solozabal/DIO/dio-apirest/apirest/src/test/java/com/dio/apirest/PersonServiceTest.java
 */
package com.dio.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.dio.apirest.exception.EntityNotFoundException;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;
import com.dio.apirest.service.PersonService;

/**
 * Test class for the PersonService.
 * 
 * This class contains unit tests for the methods in the PersonService class, 
 * verifying that the service interacts correctly with the PersonRepository.
 * 
 * The tests cover various functionalities of the PersonService, including 
 * finding, creating, updating, and deleting persons. Each test method is 
 * annotated with @Test and follows the naming convention of "test<Operation>" 
 * to clearly indicate the purpose of the test.
 * 
 * The PersonService is injected into the test class using the @InjectMocks 
 * annotation, while the PersonRepository is mocked using the @Mock annotation. 
 * This allows for controlled testing of the service methods without relying 
 * on the actual database.
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 * Since: 2023-08-23
 */
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    /**
     * Sets up the test environment by initializing the mocks before each test.
     * This method is executed before each test method to ensure a clean state.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the findAll method of the PersonService.
     * 
     * This test verifies that the findAll method calls the corresponding 
     * method in the PersonRepository exactly once.
     */
    @Test
    public void testFindAll() {
        personService.findAll();
        verify(personRepository, times(1)).findAll();
    }

    /**
     * Tests the findById method of the PersonService.
     * 
     * This test verifies that the findById method calls the corresponding 
     * method in the PersonRepository exactly once with the correct ID.
     */
    @Test
    public void testFindById() {
        Long id = 1L;
        personService.findById(id);
        verify(personRepository, times(1)).findById(id);
    }

    /**
     * Tests the create method of the PersonService.
     * 
     * This test verifies that the create method calls the save method in 
     * the PersonRepository exactly once with the correct person object.
     */
    @Test
    public void testCreate() {
        Person person = new Person();
        personService.create(person);
        verify(personRepository, times(1)).save(person);
    }

    /**
     * Tests the save method of the PersonService.
     * 
     * This test verifies that the save method calls the save method in 
     * the PersonRepository exactly once with the correct person object.
     */
    @Test
    public void testSave() {
        Person person = new Person();
        personService.save(person);
        verify(personRepository, times(1)).save(person);
    }

    /**
     * Tests the update method of the PersonService.
     * 
     * This test verifies that the update method correctly updates a person 
     * when the person exists in the repository. It checks that the returned 
     * person has the expected ID and that the appropriate methods in the 
     * PersonRepository are called.
     */
    @Test
    public void testUpdate() {
        Long id = 1L;
        Person person = new Person();
        Person updatedPerson = new Person();
        updatedPerson.setId(id);

        when(personRepository.existsById(id)).thenReturn(true);
        when(personRepository.save(person)).thenReturn(updatedPerson);

        Person result = personService.update(id, person);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(1)).save(person);
    }

    /**
     * Tests the update method of the PersonService when the person is not found.
     * 
     * This test verifies that the update method throws an EntityNotFoundException 
     * when attempting to update a person that does not exist in the repository.
     */
    @Test
    public void testUpdateNotFound() {
        Long id = 1L;
        Person person = new Person();
        when(personRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException thrownException = assertThrows(EntityNotFoundException.class, () -> personService.update(id, person));
        assertEquals("Person with id " + id + " not found.", thrownException.getMessage());
        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(0)).save(person);
    }

    /**
     * Tests the delete method of the PersonService.
     * 
     * This test verifies that the delete method calls the deleteById method 
     * in the PersonRepository exactly once when the person exists.
     */
    @Test
    public void testDelete() {
        Long id = 1L;
        when(personRepository.existsById(id)).thenReturn(true);
        personService.delete(id);
        verify(personRepository, times(1)).deleteById(id);
    }

    /**
     * Tests the delete method of the PersonService when the person is not found.
     * 
     * This test verifies that the delete method throws an EntityNotFoundException 
     * when attempting to delete a person that does not exist in the repository.
     */
    @Test
    public void testDeleteNotFound() {
        Long id = 1L;
        when(personRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException thrownException = assertThrows(EntityNotFoundException.class, () -> personService.delete(id));
        assertEquals("Person with id " + id + " not found.", thrownException.getMessage());
        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(0)).deleteById(id);
    }
}