package com.dio.apirest; // Ensure the package matches the file path

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dio.apirest.controller.PersonController;
import com.dio.apirest.exception.BadRequestException;
import com.dio.apirest.exception.NotFoundException;
import com.dio.apirest.service.PersonService;

/**
 * This class contains unit tests for the GlobalExceptionHandler.
 * It tests the handling of exceptions thrown by the PersonController
 * when interacting with the PersonService.
 * 
 * The tests ensure that the appropriate HTTP responses are returned
 * for different types of exceptions, including NotFoundException,
 * BadRequestException, and generic RuntimeExceptions.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@WebMvcTest(PersonController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    /**
     * Sets up the test environment before each test method.
     * This method resets the mocked PersonService to ensure a clean state
     * for each test.
     */
    @BeforeEach
    public void setup() {
        Mockito.reset(personService);
    }

    /**
     * Tests the handling of NotFoundException.
     * 
     * This test simulates a scenario where a Person is not found,
     * and verifies that the response status is 404 Not Found and
     * the content matches the expected error message.
     * 
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testHandleNotFoundException() throws Exception {
        Mockito.when(personService.findById(anyLong())).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Person not found"));
    }

    /**
     * Tests the handling of BadRequestException.
     * 
     * This test simulates a scenario where a bad request is made,
     * and verifies that the response status is 400 Bad Request and
     * the content matches the expected error message.
     * 
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testHandleBadRequestException() throws Exception {
        Mockito.when(personService.findById(anyLong())).thenThrow(new BadRequestException("Bad request"));

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad request"));
    }

    /**
     * Tests the handling of generic RuntimeExceptions.
     * 
     * This test simulates a scenario where an internal server error occurs,
     * and verifies that the response status is 500 Internal Server Error and
     * the content matches the expected error message.
     * 
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testHandleInternalServerErrorException() throws Exception {
        Mockito.when(personService.findById(anyLong())).thenThrow(new RuntimeException("Internal server error"));

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error"));
    }
}