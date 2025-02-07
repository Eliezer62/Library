package com.buixzy.mylibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);
}
