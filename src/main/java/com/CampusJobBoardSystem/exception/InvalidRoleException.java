package com.CampusJobBoardSystem.service;
import com.CampusJobBoardSystem.exception.InvalidRoleException;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final List<String> VALID_ROLES = List.of(
            "ADMIN", "STUDENT", "EMPLOYER"
    );

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User assignRole(Long userId, String role) {

        // check if role is valid
        if (!VALID_ROLES.contains(role.toUpperCase())) {
            throw new InvalidRoleException("Invalid role: " + role);
        }

        // find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // assign role
        user.setRole(role.toUpperCase());

        return userRepository.save(user);
    }

    public void validateRole(String role) {
        if (!VALID_ROLES.contains(role.toUpperCase())) {
            throw new InvalidRoleException("Role '" + role + "' does not exist.");
        }
    }
}
