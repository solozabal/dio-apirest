package com.dio.apirest;

import static org.hamcrest.Matchers.lessThan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Performance test class for the PersonController.
 * 
 * This class contains performance tests to evaluate the response times 
 * of various endpoints in the PersonController. The tests are designed 
 * to ensure that the application meets performance requirements by checking 
 * that the response times for different operations are within acceptable limits.
 * 
 * The tests use RestAssured to send HTTP requests and validate the responses.
 * 
 * Each test method is annotated with @Test and follows the naming convention 
 * of "testPerformance<Operation>" to clearly indicate the purpose of the test.
 * 
 * The performance threshold for each test is set to less than 5000 milliseconds (5 seconds).
 * 
 * Author: Pedro Solozabal
 * Version: 1.0
 * Since: 2023-08-23
 */
public class PersonControllerPerformanceTest {

    /**
     * Sets up the base URI and port for the RestAssured tests.
     * This method is executed once before all tests in this class.
     */
    @BeforeAll
    public static void setup() {
        // Configure the base URL and the correct port
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8082; // Replace with the correct port
    }

    /**
     * Tests the performance of the GET all persons endpoint.
     * 
     * This test verifies that the response status is 200 (OK) 
     * and that the response time is less than 5 seconds.
     */
    @Test
    public void testPerformanceGetAll() {
        Response response = RestAssured
            .when()
            .get("/api/person")
            .then()
            .statusCode(200)  // Verify that the response status is 200 (OK)
            .time(lessThan(5000L))  // Verify that the response time is less than 5 seconds
            .extract().response();

        System.out.println("Response time: " + response.getTime() + "ms");
    }

    /**
     * Tests the performance of the GET person by ID endpoint.
     * 
     * This test verifies that the response status is 200 (OK) 
     * and that the response time is less than 5 seconds.
     */
    @Test
    public void testPerformanceGetById() {
        Response response = RestAssured
            .when()
            .get("/api/person/1")
            .then()
            .statusCode(200)  // Verify that the response status is 200 (OK)
            .time(lessThan(5000L))  // Verify that the response time is less than 5 seconds
            .extract().response();

        System.out.println("Response time: " + response.getTime() + "ms");
    }

    /**
     * Tests the performance of the POST create person endpoint.
     * 
     * This test verifies that the response status is 201 (Created) 
     * and that the response time is less than 5 seconds.
     */
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
            .statusCode(201)  // Verify that the response status is 201 (Created)
            .time(lessThan(5000L))  // Verify that the response time is less than 5 seconds
            .extract().response();

        System.out.println("Response time: " + response.getTime() + "ms");
    }

    /**
     * Tests the performance of the PUT update person endpoint.
     * 
     * This test verifies that the response status is 200 (OK) 
     * and that the response time is less than 5 seconds.
     */
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
            .statusCode(200)  // Verify that the response status is 200 (OK)
            .time(lessThan(5000L))  // Verify that the response time is less than 5 seconds
            .extract().response();

        System.out.println("Response time: " + response.getTime() + "ms");
    }

    /**
     * Tests the performance of the DELETE person endpoint.
     * 
     * This test verifies that the response status is 204 (No Content) 
     * and that the response time is less than 5 seconds.
     */
    @Test
    public void testPerformanceDelete() {
        Response response = RestAssured
            .when()
            .delete("/api/person/1")
            .then()
            .statusCode(204)  // Verify that the response status is 204 (No Content)
            .time(lessThan(5000L))  // Verify that the response time is less than 5 seconds
            .extract().response();

        System.out.println("Response time: " + response.getTime() + "ms");
    }
}