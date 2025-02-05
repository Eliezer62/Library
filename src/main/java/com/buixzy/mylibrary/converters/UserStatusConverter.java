package com.buixzy.mylibrary.converters;

import com.buixzy.mylibrary.enums.UserStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserStatus attribute) {
        return attribute.getStatusName();
    }

    @Override
    public UserStatus convertToEntityAttribute(String dbData) {
        return UserStatus.fromString(dbData);
    } 
}
