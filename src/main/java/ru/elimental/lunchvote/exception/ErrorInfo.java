package ru.elimental.lunchvote.exception;

import lombok.Getter;

@Getter
public class ErrorInfo {

    private final String status = "error";
    private final String message;
    private final String[] details;

    public ErrorInfo(String message, String[] details) {
        this.message = message;
        this.details = details;
    }
}
