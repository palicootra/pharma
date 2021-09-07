package com.example.ProjetFInal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(SortieIntrouvaleException.class)
    public ResponseEntity<?> handleSortiIntrouvableException(
            SortieIntrouvaleException e, WebRequest request){
        ApiException apiException = new ApiException( e.getMessage(),request.getDescription(false),new Date());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


}
