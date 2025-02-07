package com.buixzy.mylibrary.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.buixzy.mylibrary.Impl.UserDetailsImp;
import com.buixzy.mylibrary.entities.User;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = rep.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("E-mail não encontrado"));
        return new UserDetailsImp(user);
    }
    
}
