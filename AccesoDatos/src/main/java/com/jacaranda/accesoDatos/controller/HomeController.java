package com.jacaranda.accesoDatos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Mapea la URL raíz "/" a la plantilla de inicio.
     * @return El nombre de la plantilla Thymeleaf a renderizar (index.html).
     */
    @GetMapping("/")
    public String home() {
        // Spring Boot buscará la plantilla en: src/main/resources/templates/index.html
        return "index"; 
    }
}