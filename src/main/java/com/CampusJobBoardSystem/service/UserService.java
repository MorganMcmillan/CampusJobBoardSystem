package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    void createUser(User user);
    void updateUser(long id, User user);
    void deleteUser(long id);
}
