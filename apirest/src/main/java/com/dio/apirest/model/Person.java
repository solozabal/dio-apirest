package com.dio.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representa uma pessoa no sistema.
 * 
 * Esta classe é uma entidade JPA que mapeia a tabela de pessoas no banco de dados.
 * Ela contém informações básicas sobre uma pessoa, incluindo nome e idade.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Age is mandatory")
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private Integer age;

    /**
     * Obtém o identificador único da pessoa.
     * 
     * @return O identificador único da pessoa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da pessoa.
     * 
     * @param id O identificador único a ser atribuído à pessoa.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da pessoa.
     * 
     * @return O nome da pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da pessoa.
     * 
     * @param name O nome a ser atribuído à pessoa.
     * @throws IllegalArgumentException se o nome for nulo ou vazio.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a idade da pessoa.
     * 
     * @return A idade da pessoa.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Define a idade da pessoa.
     * 
     * @param age A idade a ser atribuída à pessoa.
     * @throws IllegalArgumentException se a idade for negativa.
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}