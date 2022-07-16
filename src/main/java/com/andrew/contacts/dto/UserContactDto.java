package com.andrew.contacts.dto;

/**
 * @author andrew
 */
public record UserContactDto(
    Long userId,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    AddressDto address
) {}
