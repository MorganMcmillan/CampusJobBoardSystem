package com.CampusJobBoardSystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for JWT token management.
 * Externalized configuration for better security and maintainability.
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Secret key for signing JWT tokens.
     * Should be configured in application.properties or environment variables.
     * Minimum 256 bits (32 bytes) for HS256 algorithm.
     */
    private String secret = "weArbFwjQiuieiUtUgKGYFUYruUIHIQNiqQihiHqiuQqnbXyZ123456789ABCDEF";

    /**
     * Token expiration time in milliseconds.
     * Default: 1 hour (3600000 ms)
     */
    private long expirationMs = 3600000L;

    // Getters and Setters
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationMs() {
        return expirationMs;
    }

    public void setExpirationMs(long expirationMs) {
        this.expirationMs = expirationMs;
    }
}

