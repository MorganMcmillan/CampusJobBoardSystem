package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    private Role role;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "User status cannot be null")
    private UserStatus status;

    // Constructors
    public User() {}

    public User(String fullName, String email, Role role, UserStatus status) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
