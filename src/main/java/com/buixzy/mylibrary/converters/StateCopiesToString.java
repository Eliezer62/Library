package com.buixzy.mylibrary.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.buixzy.mylibrary.enums.StateCopies;

@Converter(autoApply = true)
public class StateCopiesToString implements AttributeConverter<StateCopies, String> {
    @Override
    public String convertToDatabaseColumn(StateCopies attribute) {
        return attribute.getStateName();
    }

    @Override
    public StateCopies convertToEntityAttribute(String dbData) {
        return StateCopies.fromString(dbData);
    }

}
