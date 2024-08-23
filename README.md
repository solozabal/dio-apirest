# DIO API REST

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar informações de pessoas. A API permite criar, ler, atualizar e deletar registros de pessoas.

## Funcionalidades

- **Criar Pessoa**: Adiciona uma nova pessoa ao sistema.
- **Listar Pessoas**: Retorna uma lista de todas as pessoas cadastradas.
- **Buscar Pessoa por ID**: Retorna os detalhes de uma pessoa específica pelo seu ID.
- **Atualizar Pessoa**: Atualiza as informações de uma pessoa existente.
- **Deletar Pessoa**: Remove uma pessoa do sistema.

## Diagrama UML

```mermaid
classDiagram
    class Person {
        Long id
        String name
        int age
    }

    PersonController --> PersonService
    PersonService --> PersonRepository
    PersonRepository --> Person

    class PersonController {
        +List~Person~ getAll()
        +ResponseEntity~Person~ getById(Long id)
        +ResponseEntity~Person~ create(Person person)
        +ResponseEntity~Person~ update(Long id, Person person)
        +ResponseEntity~Void~ delete(Long id)
    }

    class PersonService {
        +List~Person~ findAll()
        +Person findById(Long id)
        +Person save(Person person)
        +Person update(Long id, Person person)
        +void delete(Long id)
    }

    class PersonRepository {
        +List~Person~ findAll()
        +Optional~Person~ findById(Long id)
        +Person save(Person person)
        +void deleteById(Long id)
    }

## Endpoints
 
GET /api/person: Retorna todas as pessoas.
GET /api/person/{id}: Retorna uma pessoa pelo ID.
POST /api/person: Cria uma nova pessoa.
PUT /api/person/{id}: Atualiza uma pessoa existente.
DELETE /api/person/{id}: Deleta uma pessoa pelo ID.

## Exceções
BadRequestException: Lançada quando há um erro de validação nos dados da requisição.
EntityNotFoundException: Lançada quando uma entidade não é encontrada.
GlobalExceptionHandler: Manipula exceções globais na aplicação.
NotFoundException: Lançada quando um recurso não é encontrado.
