package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.UserExistsException;
import com.CampusJobBoardSystem.exception.UserNotFoundException;
import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.model.UserStatus;
import com.CampusJobBoardSystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, User user) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("User by id " + id + " not found.");
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("User by id " + id + " not found.");
        userRepository.deleteById(id);
    }
}
