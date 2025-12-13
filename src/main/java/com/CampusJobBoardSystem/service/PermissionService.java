package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;
import org.springframework.stereotype.Service;

/**
 * Service for handling authorization and permission checks.
 *
 * @version 1.0
 */
@Service
public class PermissionService {

    private final AuthenticationService authenticationService;

    public PermissionService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Checks if the current user can modify (edit/delete) a job.
     *
     * @param job The job to check permissions for
     * @return true if user has permission, false otherwise
     */
    public boolean canModifyJob(Job job) {
        return authenticationService.getCurrentUser()
                .map(user -> isAdmin(user) || isJobOwner(user, job))
                .orElse(false);
    }

    /**
     * Checks if the current user is an administrator.
     *
     * @return true if current user is an admin
     */
    public boolean isCurrentUserAdmin() {
        return authenticationService.getCurrentUser()
                .map(this::isAdmin)
                .orElse(false);
    }

    /**
     * Checks if the current user is a student.
     *
     * @return true if current user is a student
     */
    public boolean isCurrentUserStudent() {
        return authenticationService.getCurrentUser()
                .map(user -> user.getRole() == Role.STUDENT)
                .orElse(false);
    }

    /**
     * Checks if the current user is an employer.
     *
     * @return true if current user is an employer
     */
    public boolean isCurrentUserEmployer() {
        return authenticationService.getCurrentUser()
                .map(user -> user.getRole() == Role.EMPLOYER)
                .orElse(false);
    }

    private boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }

    private boolean isJobOwner(User user, Job job) {
        return job.getEmployer() != null &&
               job.getEmployer().getId().equals(user.getId());
    }
}

