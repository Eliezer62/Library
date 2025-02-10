package com.buixzy.mylibrary.converters;

import com.buixzy.mylibrary.enums.StatusLoan;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusLoanConverter implements AttributeConverter<StatusLoan, String>{

    @Override
    public String convertToDatabaseColumn(StatusLoan attribute) {
        return attribute.getStatusName();
    }

    @Override
    public StatusLoan convertToEntityAttribute(String dbData) {
        return StatusLoan.fromString(dbData);
    }
    
}
