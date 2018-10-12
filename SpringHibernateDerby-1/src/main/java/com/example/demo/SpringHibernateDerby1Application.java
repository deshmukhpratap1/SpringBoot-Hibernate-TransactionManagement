package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringHibernateDerby1Application {

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(SpringHibernateDerby1Application.class, args);
	}
}
