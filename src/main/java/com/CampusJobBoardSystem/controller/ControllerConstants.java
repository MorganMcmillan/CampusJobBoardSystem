package com.CampusJobBoardSystem.controller;

/**
 * Central repository for controller-related constants.
 *
 * @version 1.0
 */
public final class ControllerConstants {

    private ControllerConstants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }

    // View Names
    public static final class Views {
        public static final String JOBS_LIST = "jobs/list";
        public static final String JOBS_NEW = "jobs/new";
        public static final String JOBS_VIEW = "jobs/view";

        public static final String USERS_LIST = "users/user-list";
        public static final String USERS_REGISTER = "users/register";
        public static final String USERS_LOGIN = "users/login";
        public static final String USERS_EDIT = "users/edit-form";
        public static final String USERS_PROFILE = "users/user-profile";

        public static final String APPLICATIONS_LIST = "applications/list";
        public static final String APPLICATIONS_NEW = "applications/new";
        public static final String APPLICATIONS_VIEW = "applications/view";

        private Views() {}
    }

    // Redirect Paths
    public static final class Redirects {
        public static final String JOBS = "redirect:/jobs";
        public static final String USERS = "redirect:/users";
        public static final String USERS_LOGIN = "redirect:/users/login";
        public static final String APPLICATIONS = "redirect:/applications";
        public static final String INDEX = "redirect:/index";

        private Redirects() {}
    }

    // Model Attributes
    public static final class Attributes {
        public static final String JOB = "job";
        public static final String JOBS = "jobs";
        public static final String USER = "user";
        public static final String USERS = "users";
        public static final String APPLICATION = "application";
        public static final String APPLICATIONS = "applications";
        public static final String KEYWORD = "keyword";
        public static final String CAN_EDIT = "canEdit";
        public static final String JOB_ID = "jobId";
        public static final String SUCCESS_MESSAGE = "successMessage";
        public static final String ERROR_MESSAGE = "errorMessage";

        private Attributes() {}
    }

    // Flash Messages
    public static final class Messages {
        public static final String JOB_CREATED = "Job posted successfully";
        public static final String JOB_UPDATED = "Job updated successfully";
        public static final String JOB_DELETED = "Job deleted successfully";
        public static final String JOB_CREATE_FAILED = "Failed to create job: %s";
        public static final String JOB_UPDATE_FAILED = "Failed to update job: %s";
        public static final String JOB_DELETE_FAILED = "Failed to delete job: %s";

        public static final String USER_REGISTERED = "Registration successful! Please login.";
        public static final String USER_UPDATED = "User updated successfully";
        public static final String USER_DELETED = "User deleted successfully";
        public static final String REGISTRATION_FAILED = "Registration failed: %s";
        public static final String UPDATE_FAILED = "Update failed: %s";
        public static final String DELETE_FAILED = "Delete failed: %s";

        public static final String APPLICATION_SUBMITTED = "Application submitted successfully!";
        public static final String APPLICATION_FAILED = "Failed to submit application: %s";
        public static final String MUST_LOGIN = "You must be logged in to apply for jobs";

        public static final String NO_PERMISSION = "You do not have permission to %s this job.";

        private Messages() {}
    }
}

