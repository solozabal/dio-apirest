package com.dio.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.apirest.model.Person;
import com.dio.apirest.service.PersonService;

import jakarta.validation.Valid;

/**
 * Controller responsible for managing API requests related to persons.
 * 
 * This controller provides endpoints to create, read, update, and delete information
 * about persons in the system. The operations are performed using the PersonService.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Retrieves a list of all persons.
     * 
     * This method responds to GET requests at the /api/person endpoint and returns
     * a list of Person objects.
     * 
     * @return A list of all registered persons.
     */
    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    /**
     * Retrieves a person by their identifier.
     * 
     * This method responds to GET requests at the /api/person/{id} endpoint. If the person
     * is found, it returns a Person object; otherwise, it returns a 404 status.
     * 
     * @param id The identifier of the person to be retrieved.
     * @return A ResponseEntity containing the found person or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    /**
     * Creates a new person.
     * 
     * This method responds to POST requests at the /api/person endpoint. It receives a Person
     * object in the request body, saves the new person, and returns the created person with a 201 status.
     * 
     * @param person The Person object to be created.
     * @return A ResponseEntity containing the created person and the URI of the new resource.
     */
    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity
            .created(URI.create("/api/person/" + savedPerson.getId()))
            .body(savedPerson);
    }

    /**
     * Updates an existing person.
     * 
     * This method responds to PUT requests at the /api/person/{id} endpoint. It receives a Person
     * object in the request body and updates the person corresponding to the provided identifier.
     * 
     * @param id The identifier of the person to be updated.
     * @param person The Person object containing the updated data.
     * @return A ResponseEntity containing the updated person or a 404 status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        Person existingPerson = personService.update(id, person);
        if (existingPerson != null) {
            return ResponseEntity.ok(existingPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a person by their identifier.
     * 
     * This method responds to DELETE requests at the /api/person/{id} endpoint. If the person
     * is found, it is deleted and a 204 status is returned; otherwise, a 404 status is returned.
     * 
     * @param id The identifier of the person to be deleted.
     * @return A ResponseEntity with a 204 status if the deletion is successful or a 404 status if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Person person = personService.findById(id);
        if (person != null) {
            personService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}