package com.bosonit.block5SpringBoot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block5SpringBootApplication implements CommandLineRunner {
	@Autowired
	private Bean1 bean1;

	//funcion1
	@PostConstruct
	public void init(){
		bean1.saluda();
	}

	public static void main(String[] args) {
		SpringApplication.run(Block5SpringBootApplication.class, args);
	}

	//funcion2
	@Bean
	CommandLineRunner saluda2()
	{
		return p ->
		{
			System.out.println("Hola desde clase secundaria");
		};
	}

	//funcion3
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Soy la tercera clase");
		for (String arg : args) {
			System.out.println(arg);
		}
	}


	//APARTADO 1) Los mensajes se muestran primero el bean1.saluda ya que está dentro del PostConstruct,
	//que lo que hace es inicializar esta función antes que el contexto de a la aplicación.


}
