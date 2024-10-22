package com.dio.apirest.service;

import com.dio.apirest.dto.PersonRequestDTO;
import com.dio.apirest.dto.PersonResponseDTO;
import com.dio.apirest.exception.PersonNotFoundException;
import com.dio.apirest.model.Person;
import com.dio.apirest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonResponseDTO> findAll() {
        return personRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PersonResponseDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return convertToResponseDTO(person);
    }

    @Transactional
    public PersonResponseDTO createPerson(PersonRequestDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setBirthdate(personDTO.getBirthdate());
        person = personRepository.save(person);
        return convertToResponseDTO(person);
    }

    @Transactional
    public PersonResponseDTO updatePerson(Long id, PersonRequestDTO personDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setBirthdate(personDTO.getBirthdate());
        person = personRepository.save(person);
        return convertToResponseDTO(person);
    }

    @Transactional
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
        personRepository.delete(person);
    }

    private PersonResponseDTO convertToResponseDTO(Person person) {
        PersonResponseDTO responseDTO = new PersonResponseDTO();
        responseDTO.setId(person.getId());
        responseDTO.setFirstName(person.getFirstName());
        responseDTO.setLastName(person.getLastName());
        responseDTO.setEmail(person.getEmail());
        responseDTO.setBirthdate(person.getBirthdate());
        return responseDTO;
    }
}