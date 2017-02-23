package com.ncr.geocode.controllers;

import com.ncr.geocode.exceptions.GeocodingException;
import com.ncr.geocode.models.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ GeocodingException.class, RuntimeException.class })
    protected ResponseEntity<Object> handleGeocodingException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new Error(ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
