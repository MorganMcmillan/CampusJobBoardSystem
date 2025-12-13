package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.CampusJobBoardSystem.controller.ControllerConstants.*;

/**
 * Controller for handling user-related operations including registration,
 * login, profile management, and user administration.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        logger.debug("Displaying user list");
        model.addAttribute(Attributes.USERS, userService.getAllUsers());
        return Views.USERS_LIST;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        logger.debug("Displaying registration form");
        model.addAttribute(Attributes.USER, new User());
        return Views.USERS_REGISTER;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        logger.debug("Displaying login form");
        model.addAttribute(Attributes.USER, new User());
        return Views.USERS_LOGIN;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute(Attributes.USER) User user,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        logger.info("Processing user registration for email: {}", user.getEmail());

        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors in registration form");
            return Views.USERS_REGISTER;
        }

        try {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.USER_REGISTERED);
            logger.info("User registered successfully: {}", user.getEmail());
            return Redirects.USERS_LOGIN;
        } catch (Exception e) {
            logger.error("Error during user registration", e);
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.REGISTRATION_FAILED, e.getMessage()));
            return "redirect:/users/register";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.debug("Displaying edit form for user id: {}", id);
        User user = userService.getUserById(id);
        model.addAttribute(Attributes.USER, user);
        return Views.USERS_EDIT;
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id,
                           @Valid @ModelAttribute(Attributes.USER) User user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        logger.info("Updating user with id: {}", id);

        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors in user update form");
            return Views.USERS_EDIT;
        }

        try {
            userService.updateUser(id, user);
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.USER_UPDATED);
            logger.info("User updated successfully: {}", id);
        } catch (Exception e) {
            logger.error("Error updating user", e);
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.UPDATE_FAILED, e.getMessage()));
        }

        return Redirects.USERS;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("Deleting user with id: {}", id);

        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.USER_DELETED);
            logger.info("User deleted successfully: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting user", e);
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.DELETE_FAILED, e.getMessage()));
        }

        return Redirects.USERS;
    }

    @GetMapping("/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        logger.debug("Displaying profile for user id: {}", id);
        User user = userService.getUserById(id);
        model.addAttribute(Attributes.USER, user);
        return Views.USERS_PROFILE;
    }
}