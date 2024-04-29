package com.mjc.school.service.exceptions;

public class NotUniqueFieldException extends RuntimeException{
    public NotUniqueFieldException(String message){
        super(message);
    }
}
