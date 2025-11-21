package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.UserExistsException;
import com.CampusJobBoardSystem.exception.UserNotFoundException;
import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.model.UserStatus;
import com.CampusJobBoardSystem.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String fullName, String email, Role role) throws UserExistsException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserExistsException("User with email " + email + " already exists.");
        }
        User user = new User(fullName, email, role, UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User login(String fullName, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UserNotFoundException("User " + fullName + " with email " + email + " not found.");

        User user = userOptional.get();
        if (!user.getFullName().equals(fullName))
            throw new UserExistsException("A user with email" + email + " exist, but not for " + fullName);

        return user;
    }
}
