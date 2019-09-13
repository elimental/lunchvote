package ru.elimental.lunchvote.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.elimental.lunchvote.exception.AlreadyExistsException;
import ru.elimental.lunchvote.exception.ErrorInfo;
import ru.elimental.lunchvote.exception.NotFoundException;
import ru.elimental.lunchvote.exception.TimePeriondException;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo notFound(NotFoundException e) {
        return new ErrorInfo(e.getMessage(), null);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorInfo alreadyExist(AlreadyExistsException e) {
        return new ErrorInfo(e.getMessage(), null);
    }

    @ExceptionHandler(TimePeriondException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorInfo timePeriod(TimePeriondException e) {
        return new ErrorInfo(e.getMessage(), null);
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo bindValidationError(Exception e) {
        BindingResult result = e instanceof BindException ?
                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();

        String[] details = result.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toArray(String[]::new);
        return new ErrorInfo("Binding or validation error", details);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo handleError() {
        return new ErrorInfo("Server error", null);
    }
}
