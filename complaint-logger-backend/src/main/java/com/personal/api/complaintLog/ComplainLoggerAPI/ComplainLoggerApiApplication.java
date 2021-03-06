package com.personal.api.complaintLog.ComplainLoggerAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class ComplainLoggerApiApplication {

	@Value("${spring.data.mongodb.uri}")
	private String connectionURI;

	@Value("${DBName}")
	private String databaseName;
	
	@Value("${AllowedOrigins}")
	private String allowedOrigins;

	public static void main(String[] args) {
		SpringApplication.run(ComplainLoggerApiApplication.class, args);
	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(connectionURI);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();

	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), databaseName);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() 
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH");
            }
        };
    }

}
