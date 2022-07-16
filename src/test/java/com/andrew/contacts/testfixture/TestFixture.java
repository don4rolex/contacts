package com.andrew.contacts.testfixture;

import com.andrew.contacts.dto.AddressDto;
import com.andrew.contacts.dto.UserContactDto;
import com.andrew.contacts.entity.Address;
import com.andrew.contacts.entity.Contact;
import com.andrew.contacts.entity.User;

/**
 * @author andrew
 */
final public class TestFixture {

  private TestFixture() {
  }

  public static UserContactDto testUserContactDto() {
    final var address = new AddressDto(
        "2",
        "Wickford Way",
        "E17 6ZH",
        "London",
        "London",
        "England"
    );
    return new UserContactDto(
        1L,
        "Andrew",
        "Mbata",
        "testemail@yahoo.com",
        "+447878256754",
        address
    );
  }

  public static User testUserEntity() {
    return new User("Andrew", "Mbata", testContactEntity());
  }

  private static Address testAddressEntity() {
    return new Address(
        "2",
        "Wickford Way",
        "E17 6ZH",
        "London",
        "London",
        "England"
    );
  }

  private static Contact testContactEntity() {
    return new Contact("testemail@yahoo.com", "+447878256754", testAddressEntity());
  }
}
