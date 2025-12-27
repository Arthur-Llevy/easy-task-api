package com.example.demo.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.TaskInternalServerErrorException;
import com.example.demo.exception.TaskMissingRequestBodyException;
import com.example.demo.exception.TaskNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(TaskInternalServerErrorException.class)
    public ResponseEntity<String> taskInternalServerErrorException(TaskInternalServerErrorException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(TaskMissingRequestBodyException.class)
    public ResponseEntity<String> taskMissingRequestBodyException(TaskMissingRequestBodyException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
