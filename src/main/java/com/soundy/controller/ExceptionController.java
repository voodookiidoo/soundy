package com.soundy.controller;

import com.soundy.dto.exception.OwnerInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(OwnerInvalidException.class)
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<>("%s is not the owner!", HttpStatus.METHOD_NOT_ALLOWED);
    }


}
