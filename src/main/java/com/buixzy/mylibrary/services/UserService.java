package com.buixzy.mylibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository rep;
}
