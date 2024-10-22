package com.dio.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dio.apirest.exception.EntityNotFoundException;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

/**
 * This file contains the implementation of the PersonService class.
 * The PersonService class is responsible for handling operations related to persons in the API.
 * It provides methods for creating, updating, deleting, and retrieving person data.
 * The class is located in the package com.dio.apirest.service.
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Retrieves all persons from the database with pagination.
     * 
     * @param pageable the pagination information
     * @return a page of Person objects.
     */
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    /**
     * Retrieves a Person by its ID.
     * 
     * @param id the ID of the Person to retrieve
     * @return the Person object with the specified ID
     * @throws EntityNotFoundException if the Person with the given ID is not found
     */
    public Person findById(Long id) {
        return personRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Person with id " + id + " not found."));
    }

    /**
     * Creates a new Person in the database.
     * 
     * @param person the Person object to create
     * @return the created Person object
     */
    public Person create(Person person) {
        validatePerson(person);
        return personRepository.save(person);
    }

    /**
     * Updates an existing Person by its ID.
     * 
     * @param id     the ID of the Person to update
     * @param person the Person object with updated details
     * @return the updated Person object
     * @throws EntityNotFoundException if the Person with the given ID is not found
     */
    public Person update(Long id, Person person) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
        validatePerson(person);
        person.setId(id);
        return personRepository.save(person);
    }

    /**
     * Deletes a Person by its ID.
     * 
     * @param id the ID of the Person to delete
     * @throws EntityNotFoundException if the Person with the given ID is not found
     */
    public void delete(Long id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
        personRepository.deleteById(id);
    }

    /**
     * Validates the Person object.
     * 
     * @param person the Person object to validate
     * @throws IllegalArgumentException if the Person object is invalid
     */
    private void validatePerson(Person person) {
        if (person.getName() == null || person.getName().isEmpty()) {
            throw new IllegalArgumentException("Person name cannot be null or empty.");
        }
        // Add more validations as needed
    }
}