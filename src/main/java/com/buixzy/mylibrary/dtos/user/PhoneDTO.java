package com.buixzy.mylibrary.dtos.user;

import com.buixzy.mylibrary.entities.user.Phone;

public record PhoneDTO(
    String identification,
    String ddi,
    String ddd,
    String number
) {
    public Phone toPhone() {
        Phone phone = new Phone();
        phone.setIdentification(identification);
        phone.setDdi(ddi);
        phone.setDdd(ddd);
        phone.setNumber(number);
        
        return phone;
    }
}
