package com.buixzy.mylibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.OverdueFee;

@Repository
public interface OverdueFeeRepository extends JpaRepository<OverdueFee, Long> {
    @Query("SELECT o FROM OverdueFee o LEFT JOIN User u WHERE u.id = ?1")
    List<OverdueFee> findAllByUserId(Long id);

}
