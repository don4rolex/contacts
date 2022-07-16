package com.andrew.contacts.service;

import com.andrew.contacts.dto.AddressDto;
import com.andrew.contacts.dto.UserContactDto;
import com.andrew.contacts.entity.Address;
import com.andrew.contacts.entity.Contact;
import com.andrew.contacts.entity.User;

/**
 * @author andrew
 */
final public class UserContactMapper {

  private UserContactMapper() {
  }

  public static UserContactDto mapToUserContactDto(User user) {
    return new UserContactDto(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getContact().getEmail(),
        user.getContact().getPhoneNumber(),
        mapToAddressDto(user.getContact().getAddress())
    );
  }

  public static User mapToUserEntity(UserContactDto dto) {
    final var contact = new Contact(
        dto.email(),
        dto.phoneNumber(),
        mapToAddressEntity(dto.address())
    );

    return new User(dto.firstName(), dto.lastName(), contact);
  }

  private static AddressDto mapToAddressDto(Address address) {
    return new AddressDto(
        address.getHouseNumber(),
        address.getStreetName(),
        address.getPostCode(),
        address.getCity(),
        address.getState(),
        address.getCountry()
    );
  }

  private static Address mapToAddressEntity(AddressDto dto) {
    return new Address(
        dto.houseNumber(),
        dto.streetName(),
        dto.postCode(),
        dto.city(),
        dto.state(),
        dto.country()
    );
  }
}
