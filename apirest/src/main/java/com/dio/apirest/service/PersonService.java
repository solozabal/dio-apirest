package com.dio.apirest.service;

import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
