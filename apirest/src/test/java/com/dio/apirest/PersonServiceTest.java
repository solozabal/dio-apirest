package com.dio.apirest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.dio.apirest.dto.PersonRequestDTO;
import com.dio.apirest.dto.PersonResponseDTO;
import com.dio.apirest.exception.PersonNotFoundException;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;
import com.dio.apirest.service.PersonService;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Person createPerson(Long id, String firstName, String lastName, String email, LocalDate birthdate) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setBirthdate(birthdate);
        return person;
    }

    private PersonRequestDTO createPersonRequestDTO(String firstName, String lastName, String email, LocalDate birthdate) {
        PersonRequestDTO personDTO = new PersonRequestDTO();
        personDTO.setFirstName(firstName);
        personDTO.setLastName(lastName);
        personDTO.setEmail(email);
        personDTO.setBirthdate(birthdate);
        return personDTO;
    }

    @Test
    public void testFindAll() {
        Person person1 = createPerson(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));
        Person person2 = createPerson(2L, "Jane", "Doe", "jane.doe@example.com", LocalDate.of(1992, 2, 2));

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<PersonResponseDTO> persons = personService.findAll();
        assertEquals(2, persons.size());
    }

    @Test
    public void testFindById() {
        Person person = createPerson(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonResponseDTO personDTO = personService.findById(1L);
        assertNotNull(personDTO);
        assertEquals("John", personDTO.getFirstName());
    }

    @Test
    public void testFindByIdNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> personService.findById(1L));
        assertNotNull(exception);
    }

    @Test
    public void testCreatePerson() {
        PersonRequestDTO personDTO = createPersonRequestDTO("John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));
        Person person = createPerson(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));

        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonResponseDTO createdPerson = personService.createPerson(personDTO);
        assertNotNull(createdPerson.getId());
        assertEquals("John", createdPerson.getFirstName());
    }

    @Test
    public void testUpdatePerson() {
        PersonRequestDTO personDTO = createPersonRequestDTO("John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));
        Person person = createPerson(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonResponseDTO updatedPerson = personService.updatePerson(1L, personDTO);
        assertNotNull(updatedPerson);
        assertEquals("John", updatedPerson.getFirstName());
    }

    @Test
    public void testDeletePerson() {
        Person person = createPerson(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1));

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        doNothing().when(personRepository).delete(person);

        personService.deletePerson(1L);
        verify(personRepository, times(1)).delete(person);
    }

    @Test
    public void testDeletePersonNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> personService.deletePerson(1L));
        assertNotNull(exception);
    }
}