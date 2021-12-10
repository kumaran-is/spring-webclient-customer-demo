package com.demo.config;

import static com.demo.constants.ApplicationConstants.BASE_SERVICE_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return  WebClient.builder()
        		.baseUrl(BASE_SERVICE_URL)
        		.build();
    }
}
