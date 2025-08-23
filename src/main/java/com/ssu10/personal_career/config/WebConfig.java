package com.ssu10.personal_career.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("WebConfig 실행 중");
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*") // GET, POST, PUT, DELETE, OPTIONS 모두 허용
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600); // preflight 캐시 1시간
            }
        };
    }
}

