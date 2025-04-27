package org.example.chromaglambackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")                            // <— all your API endpoints
                .allowedOrigins("http://localhost:4200")          // <— Angular dev server
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*");
    }
}