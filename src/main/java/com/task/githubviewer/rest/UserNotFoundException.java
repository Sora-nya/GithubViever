package com.task.githubviewer.rest;

public class UserNotFoundException extends RuntimeException {
    private final String reason;

    public UserNotFoundException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
