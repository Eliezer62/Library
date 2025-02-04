package com.buixzy.mylibrary.controllers;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.buixzy.mylibrary.dtos.ErrorDTO;
import com.buixzy.mylibrary.exceptions.AuthorNotFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerAuthorNotFound(AuthorNotFoundException e)
    {
        ErrorDTO dto = new ErrorDTO(404, new Timestamp(System.currentTimeMillis()), "Autor não encontrado");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
