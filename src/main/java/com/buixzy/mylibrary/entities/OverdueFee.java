package com.buixzy.mylibrary.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.buixzy.mylibrary.converters.OverdueStatusConverter;
import com.buixzy.mylibrary.enums.OverdueStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "overduefees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OverdueFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String reason;

    private Date dueDate;

    private Date paidDate;

    private Integer loanDaysOverdue;

    private String notes;

    @Convert(converter = OverdueStatusConverter.class)
    private OverdueStatus status;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Loan loan;

    @JsonProperty("user")
    public Map<String, Object> userJson() {
        return new HashMap<>(Map.of(
            "id", user.getId(),
            "name", user.getName(),
            "cpf", user.getCpf(),
            "cardId", user.getCardId()
        ));
    }

    @JsonProperty("loan")
    public Map<String, Object> loanJson() {
        return new HashMap<>(Map.of(
            "id", loan.getId(),
            "loanDate", loan.getLoanDate(),
            "dueDate", loan.getDueDate(),
            "returnDate", loan.getReturnDate(),
            "copyCode", loan.getCopy().getCode()
        ));
    }
}
