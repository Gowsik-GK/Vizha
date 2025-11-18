package com.vizha.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.vizha.*" })
@EntityScan(basePackages = { "com.vizha.model" })
@EnableJpaRepositories(basePackages = { "com.vizha.repository" })
public class VizhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VizhaApplication.class, args);
	}
} 