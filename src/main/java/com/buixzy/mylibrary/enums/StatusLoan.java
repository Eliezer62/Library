package com.buixzy.mylibrary.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusLoan {
    ACTIVE("active"), RETURNED("returned"), OVERDUE("overdue");
    
    private String statusName;

    private StatusLoan(String name) {
        this.statusName = name;
    }
    
    @JsonValue
    public String getStatusName() {
        return this.statusName;
    }

    public static StatusLoan fromString(String name) {
        var status = StatusLoan.values();
        for (StatusLoan s: status) {
            if(s.getStatusName().equalsIgnoreCase(name))
                return s;
        }
        throw new IllegalArgumentException("Invalid status: " + name);
    }
}
