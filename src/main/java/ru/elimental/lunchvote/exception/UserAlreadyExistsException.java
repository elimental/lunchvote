package ru.elimental.lunchvote.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {

    private String msg;
}
