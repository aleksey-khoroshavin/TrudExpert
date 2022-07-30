package ru.trudexpert.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TrudExpertServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrudExpertServerApplication.class, args);
	}

}
