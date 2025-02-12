package com.buixzy.mylibrary.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.LoanDTO;
import com.buixzy.mylibrary.entities.Loan;
import com.buixzy.mylibrary.enums.StatusLoan;
import com.buixzy.mylibrary.exceptions.LoanNotFoundException;
import com.buixzy.mylibrary.repositories.LoanRepository;

@Service
public class LoanService {
    @Autowired
    private LoanRepository rep;

    @Autowired
    private UserService servUser;

    @Autowired
    private CopyService servCopy;

    public Loan createByDTO(LoanDTO dto) {
        Loan loan =  dto.toLoan();
        loan.setUser(servUser.findById(dto.userId()));
        loan.setCopy(servCopy.findById(dto.copyId()));

        return rep.save(loan);
    }

    public List<Loan> findAll() {
        return rep.findAll();
    }

    public List<Loan> findAllByUserId(Long id) {
        return rep.findAllByUserId(id);
    }

    public Loan findById(Long id) {
        return rep.findById(id).orElseThrow(LoanNotFoundException::new);
    }

    public Loan patch(Long id, Map<String, Object> fields) {
        Loan loan = rep.findById(id).orElseThrow(LoanNotFoundException::new);
        fields.forEach((field, value) -> {
            switch (field) {
                case "loanDate":
                    loan.setLoanDate((Date)value);
                    break;

                case "dueDate":
                    loan.setDueDate((Date)value);
                    break;
                
                case "returnDate":
                    loan.setReturnDate((Date)value);
                    break;

                case "status":
                    loan.setStatus(StatusLoan.fromString((String)value));
                    break;

                case "renew":
                    loan.setRenew((Boolean)value);
                    break;

                case "notes":
                    loan.setNotes((String)value);
                    break;

                case "userId":
                    loan.setUser(servUser.findById((Long)value));
                    break;

                case "copyId":
                    loan.setCopy(servCopy.findById((Long)id));
                    break;
            
                default:
                    break;
            }
        });

        return rep.save(loan);
    }

    public Loan update(Long id, LoanDTO dto) {
        rep.findById(id).orElseThrow(LoanNotFoundException::new);
        Loan loan = dto.toLoan();
        loan.setId(id);
        loan.setUser(servUser.findById(dto.userId()));
        loan.setCopy(servCopy.findById(dto.copyId()));

        return rep.save(loan);
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
