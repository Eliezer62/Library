package com.buixzy.mylibrary.dtos;

import java.sql.Timestamp;

public record ErrorDTO(
    Integer status,
    Timestamp timestamp,
    String message
){

}