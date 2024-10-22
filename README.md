## Visão Geral do Projeto
Este projeto é uma aplicação API REST desenvolvida com Spring Boot, simples, para gerenciar pessoas.

## Conceitos Utilizados
- **Spring Boot**: Framework que facilita a criação de aplicações Java, fornecendo configuração automática e um conjunto de boas práticas.
- **API REST**: Arquitetura para construir serviços web que utilizam HTTP para comunicação.
- **Spring Security**: Framework de segurança que fornece autenticação e autorização.
- **Spring Data JPA**: Abstração sobre JPA (Java Persistence API) para facilitar o acesso a dados.
- **H2 Database**: Banco de dados em memória utilizado para testes e desenvolvimento.
- **JUnit**: Framework de testes para Java.
- **Mockito**: Framework para criação de mocks em testes unitários.
- **Rest Assured**: Biblioteca para testes de APIs REST.

## Estrutura do Projeto e Responsabilidade dos Arquivos

### Arquivos de Configuração
- **pom.xml**: Arquivo de configuração do Maven que define as dependências do projeto, como Spring Boot, Spring Security, Spring Data JPA, H2 Database, entre outras.

### Recursos
- **application.properties**: Arquivo de configuração da aplicação. Define propriedades como URL do banco de dados, credenciais, porta do servidor, entre outras.

### Testes - Necessário Implementar os Testes.
- **ApirestApplicationTests.java**: Classe de testes que verifica se o contexto da aplicação carrega corretamente. Utiliza o framework JUnit para executar os testes.

### Outros Arquivos
- **request.http**: Arquivo que contém exemplos de requisições HTTP para testar a API. Inclui operações de CRUD (Create, Read, Update, Delete) para o recurso `person`.
- **.gitignore**: Arquivo que especifica quais arquivos e diretórios devem ser ignorados pelo Git.
- **.vscode/settings.json**: Arquivo de configuração do Visual Studio Code.

## Aplicação dos Conhecimentos
- **Configuração do Projeto**: Configuração inicial do projeto Spring Boot com Maven.
- **Modelagem de Dados**: Criação da entidade `Person` e mapeamento JPA.
- **Desenvolvimento de API REST**: Implementação do controlador `PersonController` para gerenciar operações CRUD.
- **Segurança**: Configuração de segurança com `UserConfig` para autenticação básica.
- **Interface de Usuário**: Criação de uma página inicial com `index.html` e `styles.css` para interação com a API.
- **Integração de Frontend e Backend**: Uso de Thymeleaf para renderizar dados do servidor no frontend.
- **Execução e Testes**: Execução da aplicação e testes das funcionalidades usando H2 Database.

## Conclusão
Este projeto é um exemplo de uma aplicação API REST construída com Spring Boot, utilizando várias bibliotecas e frameworks para fornecer funcionalidades como segurança, persistência de dados e testes. Cada arquivo tem uma responsabilidade específica, desde a configuração do projeto até a definição de regras de segurança e execução de testes.

## Diagrama UML

```mermaid
classDiagram
    class Person {
        Long id
        String name
        int age
    }

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

    PersonController --> PersonService
    PersonService --> PersonRepository
    PersonRepository --> Person
