
/**
 * This interface represents the repository for managing Person entities.
 * It extends the JpaRepository interface, providing CRUD operations for the Person entity.
 * The PersonRepository interface is annotated with @Repository to indicate that it is a Spring Data repository.
 *
 * @see JpaRepository
 * @see Person
 */
package com.dio.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.apirest.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}