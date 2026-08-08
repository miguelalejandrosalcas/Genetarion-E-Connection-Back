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
                        "https://ec-c094f9e61f034e029869900306b99827.ecs.us-east-1.on.aws")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}