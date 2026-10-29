package com.Sadetechno.jwt_module.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${appwrite.project.id}")
    private String projectId;

    @Value("${appwrite.api.key}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Appwrite-Project", projectId);
            requestTemplate.header("X-Appwrite-Key", apiKey);
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}