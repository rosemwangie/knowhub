package com.bobo.knowhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.bobo.knowhub.model")
public class KnowHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(KnowHubApplication.class, args);
	}
}