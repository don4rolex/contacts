package com.andrew.contacts.dto;

/**
 * @author andrew
 */
public record AddressDto(
    String houseNumber,
    String streetName,
    String postCode,
    String city,
    String state,
    String country
) {}