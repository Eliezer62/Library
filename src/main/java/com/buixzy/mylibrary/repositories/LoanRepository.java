package com.buixzy.mylibrary.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.Loan;
import com.buixzy.mylibrary.enums.StatusLoan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l LEFT JOIN User u  WHERE u.id = ?1")
    public List<Loan> findAllByUserId(Long id);

    @Query("SELECT l FROM Loan l LEFT JOIN Copy c WHERE c.id=?1 AND l.status='active'")
    public Optional<Loan> findActiveLoanByCopyId(Long id);

    @Query("SELECT l FROM Loan l LEFT JOIN User u WHERE u.id = ?1 AND l.status = 'active'")
    public List<Loan> findActiveLoanByUserId(Long id);

    public List<Loan> findAllByStatus(StatusLoan status);

    @Query("SELECT l FROM Loan LEFT JOIN User u, Copy c WHERE (:userId IS NULL OR u.id = :userId) AND (:status IS NULL OR l.status = :status) AND (:copyId IS NULL OR c.id = :copyId)")
    public List<Loan> findByFilterList(@Param("userId") Long userId, @Param("status") StatusLoan status, @Param("copyId") Long copyId);
}
