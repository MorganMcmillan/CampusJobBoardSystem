package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of AuthenticationService.
 * Centralizes authentication-related operations.
 *
 * @version 1.0
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private static final String ANONYMOUS_USER = "anonymousUser";

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentUser() {
        return getCurrentUserEmail()
                .flatMap(userRepository::findByEmail);
    }

    @Override
    public boolean isAuthenticated() {
        Authentication auth = getAuthentication();
        return auth != null &&
               auth.isAuthenticated() &&
               !ANONYMOUS_USER.equals(auth.getName());
    }

    @Override
    public Optional<String> getCurrentUserEmail() {
        if (!isAuthenticated()) {
            return Optional.empty();
        }

        Authentication auth = getAuthentication();
        return Optional.ofNullable(auth.getName());
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

