package com.buixzy.mylibrary.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    ACTIVE("active"), DISABLED("disabled"), LOCKED("locked"), EXPIRED("expired");
    
    private String statusName;

    private UserStatus(String name) {
        this.statusName = name;
    }

    @JsonValue
    public String getStatusName() {
        return this.statusName;
    }

    public static UserStatus fromString(String name) {
        for (UserStatus status: UserStatus.values()) {
            if(status.getStatusName().equalsIgnoreCase(name)){
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid status: " + name);
    }
}
