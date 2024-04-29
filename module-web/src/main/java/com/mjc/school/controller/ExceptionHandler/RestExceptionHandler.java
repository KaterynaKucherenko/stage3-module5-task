package com.mjc.school.controller.ExceptionHandler;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(DataIntegrityViolationException exception, WebRequest request) {
        if (exception.getMessage().contains("AUTHOR")) {
            return handleExceptionInternal(exception, "Name of author is not unique", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
        if (exception.getMessage().contains("TAGS")) {
            return handleExceptionInternal(exception, "Name of tag is not unique", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

        }
        if (exception.getMessage().contains("NEWS")) {
            return handleExceptionInternal(exception, "Name of title is not unique", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
        return handleExceptionInternal(exception, "Value is not unique", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, "The requested resource could not be found.", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}



