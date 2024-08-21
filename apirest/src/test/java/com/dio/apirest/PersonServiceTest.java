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

public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        personService.findAll();
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        personService.findById(id);
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    public void testCreate() {
        Person person = new Person();
        personService.create(person);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testSave() {
        Person person = new Person();
        personService.save(person);
        verify(personRepository, times(1)).save(person);
    }

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

    @Test
    public void testDelete() {
        Long id = 1L;
        when(personRepository.existsById(id)).thenReturn(true);
        personService.delete(id);
        verify(personRepository, times(1)).deleteById(id);
    }

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
