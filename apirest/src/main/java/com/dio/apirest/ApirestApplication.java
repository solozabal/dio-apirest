/**
 * This file represents the main application class for the API REST project.
 * It is responsible for starting the application and initializing the necessary components.
 * The package name for this file is com.dio.apirest.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 * @since 2022-10-01
 */
package com.dio.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApirestApplication.class, args);
    }
}
