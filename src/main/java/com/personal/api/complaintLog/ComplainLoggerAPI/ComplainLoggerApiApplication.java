package com.personal.api.complaintLog.ComplainLoggerAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class ComplainLoggerApiApplication {

	@Value("${spring.data.mongodb.uri}")
	private String connectionURI;
	
	public static void main(String[] args) {
		SpringApplication.run(ComplainLoggerApiApplication.class, args);
	}
	
	@Bean
	public MongoClient mongoClient() {
	       return MongoClients.create(connectionURI);
	   }
	

}
