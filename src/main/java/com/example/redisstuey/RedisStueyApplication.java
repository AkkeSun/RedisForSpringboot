package com.example.redisstuey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisStueyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisStueyApplication.class, args);
	}

}
