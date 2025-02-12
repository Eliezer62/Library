package com.buixzy.mylibrary.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.OverdueFeeDTO;
import com.buixzy.mylibrary.entities.OverdueFee;
import com.buixzy.mylibrary.enums.OverdueStatus;
import com.buixzy.mylibrary.exceptions.OverdueFeeNotFoundException;
import com.buixzy.mylibrary.repositories.OverdueFeeRepository;

@Service
public class OverdueFeeService {
    @Autowired
    private OverdueFeeRepository rep;

    @Autowired
    private UserService servUser;

    @Autowired
    private LoanService servLoan;

    public OverdueFee createByDTO(OverdueFeeDTO dto) {
        OverdueFee overdueFee = dto.toOverdueFee();
        overdueFee.setUser(servUser.findById(dto.userId()));
        overdueFee.setLoan(servLoan.findById(dto.loanId()));

        return rep.save(overdueFee);
    }

    public List<OverdueFee> findAll() {
        return rep.findAll();
    }

    public List<OverdueFee> findAllByUserId(Long id) {
        return rep.findAllByUserId(id);
    }

    public OverdueFee findById(Long id) {
        return rep.findById(id).orElseThrow(OverdueFeeNotFoundException::new);
    }

    public OverdueFee patch(Long id, Map<String, Object> fields) {
        OverdueFee overdueFee = rep.findById(id).orElseThrow(OverdueFeeNotFoundException::new);
        fields.forEach((field, value) -> {
            switch (field) {
                case "amount":
                    overdueFee.setAmount((Double)value);
                    break;

                case "reason":
                    overdueFee.setReason((String)value);
                    break;
                
                case "dueDate":
                    overdueFee.setDueDate((Date)value);
                    break;

                case "paidDate":
                    overdueFee.setPaidDate((Date)value);
                    break;

                case "loanDaysOverdue":
                    overdueFee.setLoanDaysOverdue((Integer)value);
                    break;

                case "notes":
                    overdueFee.setNotes((String)value);
                    break;

                case "status":
                    overdueFee.setStatus(OverdueStatus.fromString((String)value));
                    break;

                case "userId":
                    overdueFee.setUser(servUser.findById((Long)value));
                    break;

                case "loanId":
                    overdueFee.setLoan(servLoan.findById((Long)id));
                    break;
            
                default:
                    break;
            }
        });

        return rep.save(overdueFee);
    }

    public OverdueFee update(Long id, OverdueFeeDTO dto) {
        rep.findById(id).orElseThrow(OverdueFeeNotFoundException::new);
        OverdueFee overdueFee = dto.toOverdueFee();
        overdueFee.setUser(servUser.findById(dto.userId()));
        overdueFee.setLoan(servLoan.findById(dto.loanId()));
        overdueFee.setId(id);

        return rep.save(overdueFee);
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
