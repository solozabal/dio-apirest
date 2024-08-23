package com.dio.apirest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class contains unit tests for the ApirestApplication.
 * It is responsible for testing the application context loading.
 * 
 * The tests in this class ensure that the Spring application context
 * is correctly initialized and that the application can start without errors.
 * 
 * @version 1.0
 * @since 2022-10-01
 */
@SpringBootTest(classes = ApirestApplication.class)
class ApirestApplicationTests {

    /**
     * Test method to verify that the application context loads successfully.
     * 
     * This method is executed by the JUnit testing framework to ensure
     * that the context loads without any issues.
     */
    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }
}