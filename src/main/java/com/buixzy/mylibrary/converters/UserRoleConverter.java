package com.buixzy.mylibrary.converters;

import com.buixzy.mylibrary.enums.UserRole;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String>{

    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return attribute.getRoleName();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return UserRole.fromString(dbData);
    }
    
}
