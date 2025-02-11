package com.buixzy.mylibrary.converters;

import com.buixzy.mylibrary.enums.OverdueStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OverdueStatusConverter implements AttributeConverter<OverdueStatus, String> {

    @Override
    public String convertToDatabaseColumn(OverdueStatus attribute) {
        return attribute.getStatusName();
    }

    @Override
    public OverdueStatus convertToEntityAttribute(String dbData) {
        return OverdueStatus.fromString(dbData);
    }

}
