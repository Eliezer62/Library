package com.buixzy.mylibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

}
