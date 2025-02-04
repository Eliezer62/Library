package com.buixzy.mylibrary.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buixzy.mylibrary.dtos.AuthorDTO;
import com.buixzy.mylibrary.entities.Author;
import com.buixzy.mylibrary.services.AuthorService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    AuthorService serv;

    @PostMapping("/author")
    public ResponseEntity<Author> create(@RequestBody AuthorDTO dto)
    {
        return new ResponseEntity<Author>(serv.createByDTO(dto), HttpStatus.CREATED);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> showAll()
    {
        return new ResponseEntity<List<Author>>(serv.findAll(), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> show(@PathVariable("id") Long id) {
        return new ResponseEntity<Author>(serv.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/author/{id}")
    public ResponseEntity<Author> patch(@PathVariable("id") Long id, @RequestBody Map<String, Object> author) {
        return new ResponseEntity<Author>(serv.patchById(id, author), HttpStatus.OK);
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> put(@PathVariable("id") Long id, @RequestBody AuthorDTO dto) {
        return new ResponseEntity<Author>(serv.updateById(id, dto), HttpStatus.OK);
    }
    
    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        serv.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
