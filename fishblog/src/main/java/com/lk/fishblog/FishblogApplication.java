package com.lk.fishblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FishblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishblogApplication.class, args);
	}

}
