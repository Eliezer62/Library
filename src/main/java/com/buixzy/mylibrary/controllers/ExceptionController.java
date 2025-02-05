package com.buixzy.mylibrary.controllers;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.buixzy.mylibrary.dtos.ErrorDTO;
import com.buixzy.mylibrary.exceptions.AuthorNotFoundException;
import com.buixzy.mylibrary.exceptions.BookNotFoundException;
import com.buixzy.mylibrary.exceptions.CopyNotFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerAuthorNotFound(AuthorNotFoundException e)
    {
        ErrorDTO dto = new ErrorDTO(404, new Timestamp(System.currentTimeMillis()), "Autor não encontrado");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerBookNotFound(BookNotFoundException e)
    {
        ErrorDTO dto = new ErrorDTO(404, new Timestamp(System.currentTimeMillis()), "Livro não encontrado");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CopyNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerAuthorNotFound(CopyNotFoundException e)
    {
        ErrorDTO dto = new ErrorDTO(404, new Timestamp(System.currentTimeMillis()), "Copia não encontrada");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
