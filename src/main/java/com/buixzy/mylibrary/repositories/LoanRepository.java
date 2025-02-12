package com.buixzy.mylibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l LEFT JOIN User u  WHERE u.id = ?1")
    public List<Loan> findAllByUserId(Long id);
}
