package ru.elimental.lunchvote.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorInfo {

    private final String status = "error";
    private String message;
}
