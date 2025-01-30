package com.bobo.knowhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.bobo.knowhub.database.repository")  // Specify where your repositories are
@EntityScan("com.bobo.knowhub.database.model")  // Specify where your entities are located
public class KnowHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(KnowHubApplication.class, args);
	}
}
