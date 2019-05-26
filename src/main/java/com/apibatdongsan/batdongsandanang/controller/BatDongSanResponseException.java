package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.exception.BatDongSanException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class BatDongSanResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BatDongSanException.class)
    protected ResponseEntity<Object> handleException(BatDongSanException exception, WebRequest webRequest) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
