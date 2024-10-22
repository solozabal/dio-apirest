package com.dio.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.apirest.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}