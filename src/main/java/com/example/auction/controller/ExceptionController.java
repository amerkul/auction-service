package com.example.auction.controller;


import com.example.auction.exception.CustomError;
import com.example.auction.exception.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError notFound(CustomNotFoundException e) {
        return new CustomError(404, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError internalServerError(Exception e) {
        return new CustomError(500, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError badRequest(IllegalArgumentException e) {
        return new CustomError(400, e.getMessage());
    }

}
