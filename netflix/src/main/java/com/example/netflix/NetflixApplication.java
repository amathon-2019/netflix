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

/*
spring.datasource.url=jdbc:mysql://13.124.122.246:3306/netflix?useUnicode=true&zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=utf8&useSSL=false
spring.datasource.username=netflix
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

*/