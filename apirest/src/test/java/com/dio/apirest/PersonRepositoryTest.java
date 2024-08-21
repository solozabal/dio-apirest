/**
 * This file represents the test class for the PersonRepository class in the apirest package.
 * It contains test cases for the methods implemented in the PersonRepository class.
 */
package com.dio.apirest; 

import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    public void setup() {
        person = new Person();
        person.setName("John Doe");
        person.setAge(30);
    }

    @Test
    public void testSavePerson() {
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        Person savedPerson = personRepository.save(person);
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());
        assertThat(foundPerson).isPresent();
        assertThat(foundPerson.get().getName()).isEqualTo("John Doe");
    }

    @Test
    public void testDeletePerson() {
        Person savedPerson = personRepository.save(person);
        personRepository.deleteById(savedPerson.getId());
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());
        assertThat(foundPerson).isNotPresent();
    }

}