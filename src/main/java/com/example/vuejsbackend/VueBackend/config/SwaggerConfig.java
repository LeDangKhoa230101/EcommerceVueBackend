package com.example.vuejsbackend.VueBackend.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
    public GroupedOpenApi api() { 
        return GroupedOpenApi.builder()
        		.group("Retail-Bank-Account-Manager")
        		.packagesToScan("com.example.vuejsbackend.VueBackend.controller")
        		.pathsToMatch("/**")
        		.build();
    } 
} 
