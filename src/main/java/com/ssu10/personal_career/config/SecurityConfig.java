package com.ssu10.personal_career.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors() // WebConfig에 있는 CORS 설정 사용
                .and()
                .csrf().disable() // CSRF 비활성화 (개발 중에는 보통 끔)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // preflight 허용
                .anyRequest().permitAll(); // 나머지도 일단 다 허용

        return http.build();
    }
}
