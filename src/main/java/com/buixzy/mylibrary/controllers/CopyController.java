package com.buixzy.mylibrary.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.buixzy.mylibrary.dtos.CopyDTO;
import com.buixzy.mylibrary.entities.Copy;
import com.buixzy.mylibrary.services.CopyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/api")
public class CopyController {
    @Autowired
    CopyService serv;

    @PostMapping("/copy")
    public ResponseEntity<Copy> create(@RequestBody CopyDTO dto) {
        return new ResponseEntity<Copy>(serv.createByDTO(dto), HttpStatus.OK);
    }

    @GetMapping("/copy")
    public ResponseEntity<List<Copy>> showAll() {
        return new ResponseEntity<List<Copy>>(serv.findAll(), HttpStatus.OK);
    }

    @GetMapping("/copy/{id}")
    public ResponseEntity<Copy> show(@PathVariable("id") Long id) {
        return new ResponseEntity<Copy>(serv.findById(id), HttpStatus.OK);
    }
    
    @PatchMapping("/copy/{id}")
    public ResponseEntity<Copy> patch(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<Copy>(serv.patchById(id, fields), HttpStatus.OK);
    }

    @PutMapping("/copy/{id}")
    public ResponseEntity<Copy> put(@PathVariable Long id, @RequestBody CopyDTO dto) {
        return new ResponseEntity<Copy>(serv.updateById(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/copy/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serv.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    
}
