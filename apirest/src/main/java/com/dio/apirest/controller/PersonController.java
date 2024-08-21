
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

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity
            .created(URI.create("/api/person/" + savedPerson.getId()))
            .body(savedPerson);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        Person existingPerson = personService.update(id, person);
        if (existingPerson != null) {
            return ResponseEntity.ok(existingPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}