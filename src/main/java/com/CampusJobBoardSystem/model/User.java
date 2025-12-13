package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Entity representing a user in the Campus Job Board System.
 * Users can have different roles: STUDENT, EMPLOYER, or ADMIN.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_user_email", columnList = "email"),
    @Index(name = "idx_user_role", columnList = "role")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private UserStatus status;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public User() {
        this.status = UserStatus.ACTIVE; // Default status
    }

    /**
     * Constructor with all required fields.
     *
     * @param fullName User's full name
     * @param email User's email address
     * @param password User's password (should be encoded)
     * @param role User's role in the system
     * @param status User's account status
     */
    public User(String fullName, String email, String password, Role role, UserStatus status) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    // Business methods

    /**
     * Checks if the user account is active.
     *
     * @return true if user status is ACTIVE, false otherwise
     */
    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }

    /**
     * Activates the user account.
     */
    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    /**
     * Deactivates the user account.
     */
    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }

    // equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
