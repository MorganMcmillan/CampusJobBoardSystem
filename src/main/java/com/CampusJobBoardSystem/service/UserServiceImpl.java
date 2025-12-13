package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.UserExistsException;
import com.CampusJobBoardSystem.exception.UserNotFoundException;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of UserService for managing user operations.
 * Provides CRUD operations and business logic for users.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor injection for UserRepository and PasswordEncoder.
     *
     * @param userRepository Repository for user data access
     * @param passwordEncoder Encoder for password hashing
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAllUsers() {
        logger.debug("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(long id) {
        logger.debug("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void createUser(User user) {
        logger.info("Creating new user with email: {}", user.getEmail());

        // Check if user with email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.warn("Attempt to create user with existing email: {}", user.getEmail());
            throw new UserExistsException("User with email " + user.getEmail() + " already exists");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        logger.info("User created successfully with id: {}", user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateUser(long id, User user) {
        logger.info("Updating user with id: {}", id);

        if (!userRepository.existsById(id)) {
            logger.error("Attempt to update non-existent user with id: {}", id);
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        user.setId(id);
        userRepository.save(user);
        logger.info("User updated successfully with id: {}", id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteUser(long id) {
        logger.info("Deleting user with id: {}", id);

        if (!userRepository.existsById(id)) {
            logger.error("Attempt to delete non-existent user with id: {}", id);
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        userRepository.deleteById(id);
        logger.info("User deleted successfully with id: {}", id);
    }
}
