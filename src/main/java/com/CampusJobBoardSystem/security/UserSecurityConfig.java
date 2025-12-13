package com.CampusJobBoardSystem.security;

import com.CampusJobBoardSystem.repository.UserRepository;
import com.CampusJobBoardSystem.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration.
 */
@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    // URL Path Constants
    private static final String APPLICATIONS_PATH = "/applications/**";
    private static final String EMPLOYEES_API_PATH = "/api/employees/**";
    private static final String JOBS_PATH = "/jobs/**";
    private static final String ADMIN_PATH = "/admin/**";
    private static final String ALL_PATHS = "/**";

    private static final int BCRYPT_STRENGTH = 12;

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Student-only endpoints
                .requestMatchers(HttpMethod.POST, APPLICATIONS_PATH).hasRole("STUDENT")

                // Employer-only endpoints
                .requestMatchers(HttpMethod.POST, EMPLOYEES_API_PATH).hasRole("EMPLOYER")
                .requestMatchers(HttpMethod.POST, JOBS_PATH).hasRole("EMPLOYER")
                .requestMatchers(HttpMethod.DELETE, JOBS_PATH).hasRole("EMPLOYER")

                // Admin-only endpoints
                .requestMatchers(HttpMethod.GET, ADMIN_PATH).hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, ADMIN_PATH).hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, ALL_PATHS).hasRole("ADMIN")

                // Public endpoints
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/users/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/jobs", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/users/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
