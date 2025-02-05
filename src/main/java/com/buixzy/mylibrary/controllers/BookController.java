package com.buixzy.mylibrary.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buixzy.mylibrary.dtos.BookDTO;
import com.buixzy.mylibrary.entities.Book;
import com.buixzy.mylibrary.services.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService serv;
    
    @PostMapping("/book")
    public ResponseEntity<Book> create(@RequestBody BookDTO dto)
    {
        return new ResponseEntity<Book>(serv.createByDTO(dto), HttpStatus.CREATED);
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> showAll(){
        return new ResponseEntity<List<Book>>(serv.findAll(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(serv.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<Book> patch(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields){
        return new ResponseEntity<Book>(serv.patchById(id, fields), HttpStatus.OK);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> put(@PathVariable("id") Long id, @RequestBody BookDTO dto) {
        return new ResponseEntity<Book>(serv.updateById(id, dto), HttpStatus.OK);
    }
    
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        serv.deleteById(id);
        return  ResponseEntity.ok().build();
    }
    
}
