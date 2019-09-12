package ru.elimental.lunchvote.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlreadyExistsException extends RuntimeException {

    private String msg;
}
