package com.dio.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a person in the system.
 * 
 * This class is a JPA entity that maps to the persons table in the database.
 * It contains basic information about a person, including name and age.
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
     * Returns the unique identifier of the person.
     * 
     * @return The unique identifier of the person.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the person.
     * 
     * @param id The identifier to be assigned to the person.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the person.
     * 
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name The name to be assigned to the person.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the person.
     * 
     * @return The age of the person.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     * 
     * @param age The age to be assigned to the person.
     * @throws IllegalArgumentException if the age is negative.
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}