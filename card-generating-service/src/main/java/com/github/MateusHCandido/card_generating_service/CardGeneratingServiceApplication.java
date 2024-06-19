package com.github.MateusHCandido.card_generating_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CardGeneratingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardGeneratingServiceApplication.class, args);
	}

}
