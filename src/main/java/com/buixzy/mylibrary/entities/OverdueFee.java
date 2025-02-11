package com.buixzy.mylibrary.entities;

import java.util.Date;

import com.buixzy.mylibrary.converters.OverdueStatusConverter;
import com.buixzy.mylibrary.enums.OverdueStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
}
