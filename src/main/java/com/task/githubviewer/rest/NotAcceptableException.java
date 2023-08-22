package com.task.githubviewer.rest;

public class NotAcceptableException extends RuntimeException {
    private final String reason;

    public NotAcceptableException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
