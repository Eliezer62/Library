package com.buixzy.mylibrary.dtos;

import com.buixzy.mylibrary.entities.Copy;

import com.buixzy.mylibrary.enums.StateCopies;

public record CopyDTO(
    StateCopies state,
    Integer code,
    Boolean available,
    Long bookId
) {
    public Copy toCopy(){
        Copy copy = new Copy();
        copy.setState(state);
        copy.setCode(code);
        copy.setAvailable(available);
        return copy;
    }
}
