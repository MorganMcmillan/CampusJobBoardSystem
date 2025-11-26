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

    // Display user creation form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/user-form";
    }

    // Handle form submit for creating a user
    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        // Service: save a new user
        userService.createUser(user);
        return "redirect:/users";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        // Service: find user by ID
        model.addAttribute("user", userService.getUserById(id));
        return "users/user-form";
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