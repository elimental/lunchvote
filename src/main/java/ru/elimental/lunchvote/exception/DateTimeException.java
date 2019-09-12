package ru.elimental.lunchvote.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DateTimeException extends RuntimeException {

    private String msg;
}
