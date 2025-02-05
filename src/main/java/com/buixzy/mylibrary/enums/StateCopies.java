package com.buixzy.mylibrary.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.val;

public enum StateCopies {
    NEW("new"), GOOD("good"), BAD("bad"), DESTROYED("destroyed");
    private String stateName;

    private StateCopies(String name) {
        this.stateName = name;
    }

    @JsonValue
    public String getStateName() {
        return stateName;
    }
    

    public static StateCopies fromString(String name) {
        val states = StateCopies.values();
        for (StateCopies state : states) {
            if (state.getStateName().equalsIgnoreCase(name)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid state: " + name);
    }
}
