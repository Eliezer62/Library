package com.buixzy.mylibrary.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buixzy.mylibrary.dtos.UserDTO;
import com.buixzy.mylibrary.dtos.user.AddressDTO;
import com.buixzy.mylibrary.dtos.user.PhoneDTO;
import com.buixzy.mylibrary.entities.User;
import com.buixzy.mylibrary.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService serv;

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(serv.createByDTO(dto));
    }
    
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(serv.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serv.findById(id));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> patch(@PathVariable("id") Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(serv.patchById(id, fields));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(serv.updateByDTO(id, dto));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        serv.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/user/{id}/address")
    public ResponseEntity<User> createAddress(@PathVariable("id") Long id, @RequestBody AddressDTO dto) {
        return ResponseEntity.ok(serv.createAddressByDTO(id, dto));
    }

    @DeleteMapping("/user/{id}/address/{address}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id, @PathVariable("address") Long address) {
        serv.deleteAddressById(id, address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/{id}/phone")
    public ResponseEntity<User> createPhone(@PathVariable("id") Long id, @RequestBody PhoneDTO dto) {
        return ResponseEntity.ok(serv.createPhoneByDTO(id, dto));
    }

    @DeleteMapping("/user/{id}/phone/{phone}")
    public ResponseEntity<Void> deletePhone(@PathVariable("id") Long id, @PathVariable("phone") Long phoneId) {
        serv.deletePhoneById(id, phoneId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
