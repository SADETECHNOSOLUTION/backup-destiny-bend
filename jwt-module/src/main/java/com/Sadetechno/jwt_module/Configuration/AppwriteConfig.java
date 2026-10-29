package com.Sadetechno.jwt_module.config;

import io.appwrite.Client;
import io.appwrite.services.Databases;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppwriteConfig {

    @Value("${appwrite.endpoint}")
    private String endpoint;

    @Value("${appwrite.project.id}")
    private String projectId;

    @Value("${appwrite.api.key}")
    private String apiKey;

    @Bean
    public Client appwriteClient() {
        return new Client()
            .setEndpoint(endpoint)    // e.g., https://cloud.appwrite.io/v1
            .setProject(projectId)   // Your Project ID from the console
            .setKey(apiKey);         // Your Secret API Key
    }

    @Bean
    public Databases databases(Client client) {
        return new Databases(client);
    }
}