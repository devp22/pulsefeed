package com.example.pulsefeed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AwsClientConfig {

    @Bean
    public BedrockRuntimeClient bedrockRuntimeClient() {

        return BedrockRuntimeClient.builder()
                .region(Region.US_EAST_1) 
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
