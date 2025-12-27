package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class TaskInternalServerErrorException extends RuntimeException {
    private HttpStatus httpStatus;

    public TaskInternalServerErrorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
