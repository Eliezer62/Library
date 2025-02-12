package com.buixzy.mylibrary.dtos;

import java.util.Date;

import com.buixzy.mylibrary.entities.OverdueFee;
import com.buixzy.mylibrary.enums.OverdueStatus;

public record OverdueFeeDTO(
    Double amount,
    String reason,
    Date dueDate,
    Date paidDate,
    Integer loanDaysOverdue,
    String notes,
    OverdueStatus status,
    Long userId,
    Long loanId
) {
    public OverdueFee toOverdueFee() {
        OverdueFee overdueFee = new OverdueFee();
        overdueFee.setAmount(amount);
        overdueFee.setReason(reason);
        overdueFee.setDueDate(dueDate);
        overdueFee.setPaidDate(paidDate);
        overdueFee.setLoanDaysOverdue(loanDaysOverdue);
        overdueFee.setNotes(notes);
        overdueFee.setStatus(status);

        return overdueFee;
    }
}
