package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.DeactivatedUserException;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Example: login check
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.isActive()) {
            throw new DeactivatedUserException("Your account is deactivated. Please contact admin.");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Incorrect password");
        }

        return user;
    }

    // Example: check before performing an action
    public void ensureUserIsActive(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.isActive()) {
            throw new DeactivatedUserException("This user account is deactivated.");
        }
    }
}
