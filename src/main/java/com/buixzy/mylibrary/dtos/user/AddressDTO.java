package com.buixzy.mylibrary.dtos.user;

import com.buixzy.mylibrary.entities.user.Address;

public record AddressDTO(
    String street,
    String city,
    String state,
    String zip,
    Short apartament,
    String country,
    String identification
) {
    public Address toAddress() {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setApartament(apartament);
        address.setCountry(country);
        address.setIdentification(identification);
        
        return address;
    }


}
