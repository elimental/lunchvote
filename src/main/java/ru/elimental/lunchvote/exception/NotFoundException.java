package ru.elimental.lunchvote.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private String msg;
}