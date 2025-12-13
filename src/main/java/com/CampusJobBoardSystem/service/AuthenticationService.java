package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.User;

import java.util.Optional;

/**
 * Service for handling authentication-related operations.
 * Provides centralized access to current user information.
 *
 * @version 1.0
 */
public interface AuthenticationService {

    /**
     * Retrieves the currently authenticated user.
     *
     * @return Optional containing the current user if authenticated, empty otherwise
     */
    Optional<User> getCurrentUser();

    /**
     * Checks if there is a currently authenticated user.
     *
     * @return true if user is authenticated, false otherwise
     */
    boolean isAuthenticated();

    /**
     * Gets the email of the currently authenticated user.
     *
     * @return Optional containing the current user's email
     */
    Optional<String> getCurrentUserEmail();
}

