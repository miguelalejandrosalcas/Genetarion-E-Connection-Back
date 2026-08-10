package com.alumni.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://127.0.0.1:5502",
                        "http://localhost:5502",
                        "http://127.0.0.1:5501",
                        "http://localhost:5501",
                        "https://aurora-serverless-mysql-generation-instance-1.cf48okcygkvy.us-east-1.rds.amazonaws.com",
                        "https://main.dbt8kmtvxb7ok.amplifyapp.com")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}