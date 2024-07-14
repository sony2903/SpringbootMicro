package com.microservices.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TestAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAuthApplication.class, args);
	}

}
