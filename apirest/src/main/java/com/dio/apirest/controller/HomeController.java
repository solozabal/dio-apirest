package com.dio.apirest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Retorna o nome do arquivo HTML (index.html) sem a extensão
    }
}