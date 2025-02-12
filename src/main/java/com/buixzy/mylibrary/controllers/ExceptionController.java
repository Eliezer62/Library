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
import com.buixzy.mylibrary.exceptions.CopyYetLoaned;
import com.buixzy.mylibrary.exceptions.LoansActiveExcessException;
import com.buixzy.mylibrary.exceptions.OverdueFeeNotPaid;
import com.buixzy.mylibrary.exceptions.UserNotActiveException;
import com.buixzy.mylibrary.exceptions.UserNotFoundException;

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerUserNotFound(UserNotFoundException e) {
        return new ResponseEntity<>(new ErrorDTO(404, new Timestamp(System.currentTimeMillis()), "Usuário não encontrado"), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(CopyYetLoaned.class)
    public ResponseEntity<ErrorDTO> handlerCopyYetLoaned(CopyYetLoaned e) {
        ErrorDTO error = new ErrorDTO(409, new Timestamp(System.currentTimeMillis()), "Copia já emprestada por outro usuário");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OverdueFeeNotPaid.class)
    public ResponseEntity<ErrorDTO> handlerOverdueFeeNotPaid(OverdueFeeNotPaid e) {
        ErrorDTO error = new ErrorDTO(403, new Timestamp(System.currentTimeMillis()), "Multas de atraso não pagas");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LoansActiveExcessException.class)
    public ResponseEntity<ErrorDTO> handlerLoansActiveExcess(LoansActiveExcessException e) {
        ErrorDTO error = new ErrorDTO(403, new Timestamp(System.currentTimeMillis()), "Usuário possui três empréstimo ativo");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<ErrorDTO> handlerUserNotActiveException(UserNotActiveException e) {
        ErrorDTO error = new ErrorDTO(403, new Timestamp(System.currentTimeMillis()), "Usuário não está ativo, consulte a biblioteca");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
