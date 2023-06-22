//package com.bosonit.block10cors.exceptions;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/addPerson")
//                .allowedOrigins("https://cdpn.io")  // Reemplaza con tu dominio específico
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
//    }
//}