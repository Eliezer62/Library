package com.buixzy.mylibrary.services;

import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.repositories.CopyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CopyService {
    CopyRepository rep;
}
