package com.dio.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.apirest.exception.EntityNotFoundException; // Importa a exceção personalizada
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;

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

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    /**
     * Updates an existing Person by its ID.
     * Throws EntityNotFoundException if the Person with the given ID does not exist.
     *
     * @param id     the ID of the Person to update
     * @param person the Person object with updated details
     * @return the updated Person object
     * @throws EntityNotFoundException if the Person with the given ID is not found
     */
    
     public Person update(Long id, Person person) {
        // Verifica se o ID existe no banco de dados
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
        // Define o ID da pessoa e salva no repositório
        person.setId(id);
        return personRepository.save(person);
    }
 
    public void delete(Long id) {
        // Verifica se o ID existe antes de tentar deletar
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
        personRepository.deleteById(id);
    }
}
