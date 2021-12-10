package com.sumset.techsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sumset.techsupport.services.AdjuntosService;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class TechsupportApplication implements CommandLineRunner {
	
	@Resource
	AdjuntosService adjuntosService;

	public static void main(String[] args) {
		SpringApplication.run(TechsupportApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// Vamos a crear la carpeta para guardar los archivos
//		adjuntosService.init();
	}

	@Bean
	public WebMvcConfigurer corsCongigure() {
		return new WebMvcConfigurer() {
			//@Override
			public void addCorsMapping(CorsRegistry registry) {
				registry.addMapping("http://localhost:4200/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE");
			}
		};
	}

	
}
