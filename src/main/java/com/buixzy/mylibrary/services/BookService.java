package com.buixzy.mylibrary.services;

import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository rep;
}
