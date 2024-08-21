package com.dio.apirest; // Corrigir o pacote para corresponder ao caminho do arquivo

import com.dio.apirest.controller.PersonController;
import com.dio.apirest.exception.BadRequestException;
import com.dio.apirest.exception.NotFoundException;
import com.dio.apirest.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    public void setup() {
        Mockito.reset(personService);
    }

    @Test
    public void testHandleNotFoundException() throws Exception {
        Mockito.when(personService.findById(anyLong())).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Person not found"));
    }

    @Test
    public void testHandleBadRequestException() throws Exception {
        Mockito.when(personService.findById(anyLong())).thenThrow(new BadRequestException("Bad request"));

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad request"));
    }

    @Test
    public void testHandleInternalServerErrorException() throws Exception {
        Mockito.when(personService.findById(anyLong())).thenThrow(new RuntimeException("Internal server error"));

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error"));
    }
}