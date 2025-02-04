package com.buixzy.mylibrary.services;

import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.repositories.AuthorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {
    AuthorRepository rep;
    
}
