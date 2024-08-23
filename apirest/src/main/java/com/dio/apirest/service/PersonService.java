package com.dio.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.apirest.exception.EntityNotFoundException; // Importing the custom exception
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

/**
 * This file contains the implementation of the PersonService class.
 * The PersonService class is responsible for handling operations related to persons in the API.
 * It provides methods for creating, updating, deleting, and retrieving person data.
 * The class is located in the package com.dio.apirest.service.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Retrieves all persons from the database.
     * 
     * @return a list of all Person objects.
     */
    public List<Person> findAll() {
        return personRepository.findAll();
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
        return personRepository.save(person);
    }

    /**
     * Saves a Person object to the database.
     * 
     * @param person the Person object to save
     * @return the saved Person object
     */
    public Person save(Person person) {
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
        // Checks if the ID exists in the database
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
        // Sets the ID of the person and saves it in the repository
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
        // Checks if the ID exists before attempting to delete
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
        personRepository.deleteById(id);
    }
}