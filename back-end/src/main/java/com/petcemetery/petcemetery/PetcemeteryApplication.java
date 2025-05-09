package com.petcemetery.petcemetery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class PetCemeteryApplication implements WebMvcConfigurer{


	public static void main(String[] args) {
		SpringApplication.run(PetCemeteryApplication.class, args);
	}
}
