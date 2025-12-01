package com.CampusJobBoardSystem.service;
import com.CampusJobBoardSystem.exception.UserNotFoundException;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Find user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User with ID " + userId + " not found."));
    }

    // Find user by email
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User with email '" + email + "' not found.");
        }

        return user;
    }

    // Update user
    public User updateUser(Long userId, User updatedUser) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("Cannot update. User with ID " + userId + " does not exist."));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setActive(updatedUser.isActive());

        return userRepository.save(existingUser);
    }

    // Delete user
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Cannot delete. User with ID " + userId + " does not exist.");
        }
