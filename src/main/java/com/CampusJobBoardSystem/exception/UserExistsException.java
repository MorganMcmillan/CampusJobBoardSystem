package com.CampusJobBoardSystem.exception;

/// Used to signal that a user who attempted to register already exists in the database.
public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
