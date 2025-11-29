package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.service.UserService;
import com.CampusJobBoardSystem.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Show all users
    @GetMapping
    public String listUsers(Model model) {
        // Service: return list of all users
        model.addAttribute("users", userService.getAllUsers());
        return "users/user-list";
    }

    // Display user registration form
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "users/login";
    }

    // Display user login form
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "users/login";
    }

    // Handle form submit for registering a user
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        // Service: save a new user
        userService.createUser(user);
        return "redirect:/users";
    }

    // Handle form submit for logging in a user
    // Give user a JWT for authentication
    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        // TODO: figure out how JWTs are used
        return "redirect:/users";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        // Service: find user by ID
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit-form";
    }

    // Handle form submit for update
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        // Service: update user data
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Service: delete user by ID
        userService.deleteUser(id);
        return "redirect:/users";
    }

    // Show user profile page
    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        // Service: return detailed profile information
        model.addAttribute("user", userService.getUserById(id));
        return "users/user-profile";
    }
}