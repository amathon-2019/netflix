package com.example.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NetflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixApplication.class, args);
	}

}
