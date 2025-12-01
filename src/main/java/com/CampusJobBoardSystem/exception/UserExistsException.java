package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.UserExistsException;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public User register(User user) {

        // Check if email is already in use
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserExistsException(
                "A user with the email '" + user.getEmail() + "' already exists."
            );
        }

        // Save user if email is unique
        return userRepository.save(user);
    }

    // Optionally: check if user exists
    public void checkUserDoesNotExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserExistsException("Email '" + email + "' is already taken.");
        }
    }
}
