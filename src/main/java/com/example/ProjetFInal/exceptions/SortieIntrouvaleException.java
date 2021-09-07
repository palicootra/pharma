package com.example.ProjetFInal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SortieIntrouvaleException extends RuntimeException {
    public SortieIntrouvaleException(String message) {
        super(message);
    }

}
