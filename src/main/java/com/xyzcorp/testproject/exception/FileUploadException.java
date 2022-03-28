package com.xyzcorp.testproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileUploadException extends RuntimeException{
    public FileUploadException(String msg){
        super("Error parsing file : " + msg);
    }
}
