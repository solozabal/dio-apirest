# API REST com Spring Boot

Este projeto é uma API REST desenvolvida em Java utilizando o framework Spring Boot para o Santander Bootcamp na DIO. A API permite realizar operações CRUD (Create, Read, Update, Delete) para gerenciar informações de pessoas.

## Estrutura do Projeto

A estrutura do projeto é organizada em pacotes que seguem a arquitetura MVC (Model-View-Controller). Abaixo está um diagrama UML que ilustra as principais classes e suas interações.

```mermaid
classDiagram
    class PersonController {
        +findAll()
        +findById(Long id)
        +create(Person person)
        +update(Long id, Person person)
        +delete(Long id)
    }
    
    class GlobalExceptionHandler {
        +handleNotFoundException(NotFoundException ex)
        +handleBadRequestException(BadRequestException ex)
        +handleInternalServerErrorException(Exception ex)
    }
    
    class Person {
        -Long id
        -String firstName
        -String lastName
        -String address
        -String gender
    }
    
    class PersonRepository {
        +findAll()
        +findById(Long id)
        +save(Person person)
        +update(Person person)
        +delete(Person person)
    }
    
    class PersonService {
        +findAll()
        +findById(Long id)
        +create(Person person)
        +update(Long id, Person person)
        +delete(Long id)
    }
    
    class ApirestApplication {
        +main(String[] args)
    }
    
    PersonController --> PersonService
    PersonService --> PersonRepository
    PersonService --> Person
    GlobalExceptionHandler --> NotFoundException
    GlobalExceptionHandler --> BadRequestException
