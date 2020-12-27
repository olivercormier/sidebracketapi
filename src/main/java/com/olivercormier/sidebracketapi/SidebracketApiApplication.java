package com.olivercormier.sidebracketapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SidebracketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SidebracketApiApplication.class, args);

	}
}
