package com.dio.apirest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.lessThan;

public class PersonControllerPerformanceTest {

    @BeforeAll
    public static void setup() {
        // Configure a URL base e a porta correta
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8082; // Substitua pela porta correta
    }

    @Test
    public void testPerformanceGetAll() {
        Response response = RestAssured
            .when()
            .get("/api/person")
            .then()
            .statusCode(200)  // Verifica se o status da resposta é 200 (OK)
            .time(lessThan(5000L))  // Verifica se o tempo de resposta é menor que 5 segundos
            .extract().response();

        System.out.println("Tempo de resposta: " + response.getTime() + "ms");
    }

    @Test
    public void testPerformanceGetById() {
        Response response = RestAssured
            .when()
            .get("/api/person/1")
            .then()
            .statusCode(200)  // Verifica se o status da resposta é 200 (OK)
            .time(lessThan(5000L))  // Verifica se o tempo de resposta é menor que 5 segundos
            .extract().response();

        System.out.println("Tempo de resposta: " + response.getTime() + "ms");
    }

    @Test
    public void testPerformanceCreate() {
        String personJson = "{\"name\":\"John Doe\",\"age\":30}";

        Response response = RestAssured
            .given()
            .contentType("application/json")
            .body(personJson)
            .when()
            .post("/api/person")
            .then()
            .statusCode(201)  // Verifica se o status da resposta é 201 (Created)
            .time(lessThan(5000L))  // Verifica se o tempo de resposta é menor que 5 segundos
            .extract().response();

        System.out.println("Tempo de resposta: " + response.getTime() + "ms");
    }

    @Test
    public void testPerformanceUpdate() {
        String updatedPersonJson = "{\"name\":\"John Doe Updated\",\"age\":35}";

        Response response = RestAssured
            .given()
            .contentType("application/json")
            .body(updatedPersonJson)
            .when()
            .put("/api/person/1")
            .then()
            .statusCode(200)  // Verifica se o status da resposta é 200 (OK)
            .time(lessThan(5000L))  // Verifica se o tempo de resposta é menor que 5 segundos
            .extract().response();

        System.out.println("Tempo de resposta: " + response.getTime() + "ms");
    }

    @Test
    public void testPerformanceDelete() {
        Response response = RestAssured
            .when()
            .delete("/api/person/1")
            .then()
            .statusCode(204)  // Verifica se o status da resposta é 204 (No Content)
            .time(lessThan(5000L))  // Verifica se o tempo de resposta é menor que 5 segundos
            .extract().response();

        System.out.println("Tempo de resposta: " + response.getTime() + "ms");
    }
}