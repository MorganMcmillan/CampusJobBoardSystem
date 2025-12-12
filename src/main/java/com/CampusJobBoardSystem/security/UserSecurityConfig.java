package com.CampusJobBoardSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
// This tells Spring Security to use the BCrypt hashing algorithm
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/applications/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/api/employees/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/jobs/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/jobs/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
        );

// Disable Cross-Site Request Forgery (CSRF) for REST APIs using POST/PUT/DELETE
        http.csrf(csrf -> csrf.disable());
return http.build();
    }
}
