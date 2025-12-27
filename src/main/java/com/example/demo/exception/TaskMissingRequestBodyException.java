package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class TaskMissingRequestBodyException extends RuntimeException {
    private HttpStatus httpStatus;

    public TaskMissingRequestBodyException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
