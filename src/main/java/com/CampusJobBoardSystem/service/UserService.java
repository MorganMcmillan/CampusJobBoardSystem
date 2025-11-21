package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;

public interface UserService {
    public User register(String fullName, String email, Role role);
    public User login(String fullName, String email);
}
