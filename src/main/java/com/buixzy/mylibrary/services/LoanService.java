package com.buixzy.mylibrary.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.LoanDTO;
import com.buixzy.mylibrary.entities.Loan;
import com.buixzy.mylibrary.entities.OverdueFee;
import com.buixzy.mylibrary.enums.StatusLoan;
import com.buixzy.mylibrary.enums.UserStatus;
import com.buixzy.mylibrary.exceptions.CopyYetLoaned;
import com.buixzy.mylibrary.exceptions.LoanNotFoundException;
import com.buixzy.mylibrary.exceptions.LoansActiveExcessException;
import com.buixzy.mylibrary.exceptions.OverdueFeeNotPaid;
import com.buixzy.mylibrary.exceptions.UserNotActiveException;
import com.buixzy.mylibrary.repositories.LoanRepository;

@Service
public class LoanService {
    @Autowired
    private LoanRepository rep;

    @Autowired
    private UserService servUser;

    @Autowired
    private CopyService servCopy;

    @Autowired
    OverdueFeeService serviceOverdueFee;

    public Loan createByDTO(LoanDTO dto) {
        Loan loan =  dto.toLoan();
        loan.setUser(servUser.findById(dto.userId()));
        loan.setCopy(servCopy.findById(dto.copyId()));

        /**
         * Regras de aceite
         * 1. A copia não deve ter sido emprestada
         * 2. O usuário deve estar ativo
         * 3. O usuário não deve ter multas não pagas
         * 4. O usuário deve ter até 3 empréstimos ativos
         */
        if(loan.getUser().getStatus() != UserStatus.ACTIVE) {
            throw new UserNotActiveException();
        }

        Optional<Loan> loansActiveCopy = findActiveLoanByCopyId(dto.copyId());
        if(loansActiveCopy.isPresent())
            throw new CopyYetLoaned();

        List<OverdueFee> overdueFeesNotPaid = serviceOverdueFee.findAllNotPaidByUserId(dto.userId());
        if(overdueFeesNotPaid.size() > 0)
            throw new OverdueFeeNotPaid();
        
        List<Loan> loansActives = findActiveLoanByUserId(dto.userId());
        if(loansActives.size() > 3)
            throw new  LoansActiveExcessException();

        return rep.save(loan);
    }

    public List<Loan> findAll() {
        return rep.findAll();
    }

    public List<Loan> findAllByUserId(Long id) {
        return rep.findAllByUserId(id);
    }

    public List<Loan> findAllActiveByUserId(Long id) {
        return rep.findActiveLoanByUserId(id);
    }

    public Loan findById(Long id) {
        return rep.findById(id).orElseThrow(LoanNotFoundException::new);
    }

    public Optional<Loan> findActiveLoanByCopyId(Long id) {
        return rep.findActiveLoanByCopyId(id);
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

    public List<Loan> findActiveLoanByUserId(Long userId) {
        return rep.findActiveLoanByUserId(userId);
    }

    public List<Loan> findAllByStatus(StatusLoan status) {
        return rep.findAllByStatus(status);
    }

    public List<Loan> findByFilterList(Long userId, StatusLoan status, Long copyId) {
        return rep.findByFilterList(userId, status, copyId);
    }
}
