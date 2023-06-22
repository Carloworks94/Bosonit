package com.bosonit.block10cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableFeignClients
@SpringBootApplication
public class Block7CrudValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer(){
//		return new WebMvcConfigurer(){
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/addPerson")
//						.allowedOrigins("https://cdpn.io")  // Reemplaza con tu dominio específico
//						.allowedMethods("GET", "POST", "PUT", "DELETE");
//			}
//		};
//	}
}
