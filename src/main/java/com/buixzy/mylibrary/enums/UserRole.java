package com.buixzy.mylibrary.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMIN("admin"), LIBRARY("library"), USER("user");
    
    private String roleName;

    private UserRole(String name) {
        this.roleName = name;
    }

    @JsonValue
    public String getRoleName() {
        return this.roleName;
    }

    public static UserRole fromString(String name) {
        for (UserRole role: UserRole.values()) {
            if(role.getRoleName().equalsIgnoreCase(name)){
                return role;
            }
        }

        throw new IllegalArgumentException("Invalid role: " + name);
    }
    
}
