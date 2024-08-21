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
 * Controlador responsável por gerenciar as requisições da API relacionadas a pessoas.
 * 
 * Este controlador fornece endpoints para criar, ler, atualizar e excluir informações
 * sobre pessoas no sistema. As operações são realizadas utilizando o serviço PersonService.
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
     * Obtém uma lista de todas as pessoas.
     * 
     * Este método responde a requisições GET na rota /api/person e retorna
     * uma lista de objetos Person.
     * 
     * @return Uma lista de todas as pessoas cadastradas.
     */
    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    /**
     * Obtém uma pessoa pelo seu identificador.
     * 
     * Este método responde a requisições GET na rota /api/person/{id}. Se a pessoa
     * for encontrada, retorna um objeto Person; caso contrário, retorna um status 404.
     * 
     * @param id O identificador da pessoa a ser buscada.
     * @return Uma ResponseEntity contendo a pessoa encontrada ou um status 404 se não for encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    /**
     * Cria uma nova pessoa.
     * 
     * Este método responde a requisições POST na rota /api/person. Recebe um objeto Person
     * no corpo da requisição, salva a nova pessoa e retorna a pessoa criada com um status 201.
     * 
     * @param person O objeto Person a ser criado.
     * @return Uma ResponseEntity contendo a pessoa criada e o URI do novo recurso.
     */
    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity
            .created(URI.create("/api/person/" + savedPerson.getId()))
            .body(savedPerson);
    }

    /**
     * Atualiza uma pessoa existente.
     * 
     * Este método responde a requisições PUT na rota /api/person/{id}. Recebe um objeto Person
     * no corpo da requisição e atualiza a pessoa correspondente ao identificador fornecido.
     * 
     * @param id O identificador da pessoa a ser atualizada.
     * @param person O objeto Person contendo os dados atualizados.
     * @return Uma ResponseEntity contendo a pessoa atualizada ou um status 404 se não for encontrada.
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
     * Exclui uma pessoa pelo seu identificador.
     * 
     * Este método responde a requisições DELETE na rota /api/person/{id}. Se a pessoa
     * for encontrada, ela é excluída e um status 204 é retornado; caso contrário, um status 404 é retornado.
     * 
     * @param id O identificador da pessoa a ser excluída.
     * @return Uma ResponseEntity com status 204 se a exclusão for bem-sucedida ou um status 404 se não for encontrada.
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