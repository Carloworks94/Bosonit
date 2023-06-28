package com.bosonit.block11uploaddownloadfiles;

import com.bosonit.block11uploaddownloadfiles.fichero.application.FicheroServiceImpl;
import com.bosonit.block11uploaddownloadfiles.fichero.controller.FicheroController;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);

	}


	@Bean
	public CommandLineRunner init (FicheroServiceImpl servicio){
		return args -> {
			if (args.length > 0){
				servicio.setPath(args[0]);
			}
		};
	}
}
