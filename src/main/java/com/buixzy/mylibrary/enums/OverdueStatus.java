package com.buixzy.mylibrary.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OverdueStatus {
    PENDING("pending"), PAID("paid");
    private String statusName;

    private OverdueStatus(String name) {
        this.statusName = name;
    }

    @JsonValue
    public String getStatusName() {
        return this.statusName;
    }

    public static OverdueStatus fromString(String name) {
        var status = OverdueStatus.values();
        for (OverdueStatus overdueStatus : status) {
            if(overdueStatus.getStatusName() == name)
                return overdueStatus;
        }

        throw new IllegalArgumentException("OverdueStatus invalid");
    }

}
