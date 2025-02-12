package com.buixzy.mylibrary.dtos;

import java.util.Date;

import com.buixzy.mylibrary.entities.Loan;
import com.buixzy.mylibrary.enums.StatusLoan;

public record LoanDTO(
    Date loanDate,
    Date dueDate,
    StatusLoan status,
    Boolean renew,
    String notes,
    Long copyId,
    Long userId
) {
    public Loan toLoan() {
        Loan loan = new Loan();
        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);
        loan.setStatus(status);
        loan.setRenew(renew);
        loan.setNotes(notes);

        return loan;
    }
}
