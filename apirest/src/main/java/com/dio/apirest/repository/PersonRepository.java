package com.dio.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.apirest.model.Person;

/**
 * This interface represents the repository for managing Person entities.
 * It extends the JpaRepository interface, providing CRUD operations for the Person entity.
 * The PersonRepository interface is annotated with @Repository to indicate that it is a Spring Data repository.
 * It is responsible for persisting and retrieving Person objects from the underlying database.
 *
 * @author Pedro Solozabal
 * @version 1.0
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.dio.apirest.model.Person
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}