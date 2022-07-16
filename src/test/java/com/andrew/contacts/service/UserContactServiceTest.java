package com.andrew.contacts.service;

import com.andrew.contacts.entity.User;
import com.andrew.contacts.exception.UserNotFoundException;
import com.andrew.contacts.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.andrew.contacts.testfixture.TestFixture.testUserContactDto;
import static com.andrew.contacts.testfixture.TestFixture.testUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author andrew
 */
class UserContactServiceTest {

  private UserRepository mockUserRepository;
  private UserContactService userContactService;

  @BeforeEach
  void setup() {
    mockUserRepository = mock(UserRepository.class);
    userContactService = new UserContactService(mockUserRepository);
  }

  @Test
  @DisplayName("When addUserContact method is invoked then save user contact")
  void whenAddUserContactIsInvokedThenSaveUserContact() {
    final var testUserEntity = testUserEntity();
    when(mockUserRepository.save(any(User.class)))
        .thenReturn(testUserEntity);

    final var contact = userContactService.addUserContact(testUserContactDto());

    assertEquals(contact.firstName(), testUserEntity.getFirstName());
    assertEquals(contact.lastName(), testUserEntity.getLastName());
    assertEquals(contact.email(), testUserEntity.getContact().getEmail());
    assertEquals(contact.phoneNumber(), testUserEntity.getContact().getPhoneNumber());
    assertEquals(contact.address().houseNumber(), testUserEntity.getContact().getAddress().getHouseNumber());
    assertEquals(contact.address().streetName(), testUserEntity.getContact().getAddress().getStreetName());
    assertEquals(contact.address().postCode(), testUserEntity.getContact().getAddress().getPostCode());
    assertEquals(contact.address().postCode(), testUserEntity.getContact().getAddress().getPostCode());
    assertEquals(contact.address().city(), testUserEntity.getContact().getAddress().getCity());
    assertEquals(contact.address().state(), testUserEntity.getContact().getAddress().getState());
    assertEquals(contact.address().country(), testUserEntity.getContact().getAddress().getCountry());

    verify(mockUserRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("When findUserContactById is invoked with valid user ID then return user contact")
  void whenFindUserContactByIdIsInvokedWithValidIdThenReturnContact() {
    final var userId = 1L;
    final var testUserEntity = testUserEntity();
    when(mockUserRepository.findById(userId))
        .thenReturn(Optional.of(testUserEntity));

    final var contact = userContactService.findUserContactById(userId);

    assertEquals(contact.firstName(), testUserEntity.getFirstName());
    assertEquals(contact.lastName(), testUserEntity.getLastName());
    assertEquals(contact.email(), testUserEntity.getContact().getEmail());
    assertEquals(contact.phoneNumber(), testUserEntity.getContact().getPhoneNumber());
    assertEquals(contact.address().houseNumber(), testUserEntity.getContact().getAddress().getHouseNumber());
    assertEquals(contact.address().streetName(), testUserEntity.getContact().getAddress().getStreetName());
    assertEquals(contact.address().postCode(), testUserEntity.getContact().getAddress().getPostCode());
    assertEquals(contact.address().postCode(), testUserEntity.getContact().getAddress().getPostCode());
    assertEquals(contact.address().city(), testUserEntity.getContact().getAddress().getCity());
    assertEquals(contact.address().state(), testUserEntity.getContact().getAddress().getState());
    assertEquals(contact.address().country(), testUserEntity.getContact().getAddress().getCountry());

    verify(mockUserRepository, times(1)).findById(userId);
  }


  @Test
  @DisplayName("When findUserContactById is invoked with invalid user ID then throw UserNotFoundException")
  void whenFindUserContactByIdIsInvokedWithInvalidIdThenThrowUserNotFoundException() {
    final var invalidId = 1L;
    when(mockUserRepository.findById(invalidId))
        .thenReturn(Optional.empty());

    assertThrows(
        UserNotFoundException.class,
        () -> userContactService.findUserContactById(invalidId),
        "User %d not found".formatted(invalidId)
    );

    verify(mockUserRepository, times(1)).findById(invalidId);
  }

  @Test
  @DisplayName("When findUserContactsByIds is invoked with valid list of IDs then return list of contacts")
  void whenFindUserContactsByIdsIsInvokedThenReturnListOfContacts() {
    final var userIdList = List.of(1L, 2L);
    final var userEntityList = List.of(testUserEntity(), testUserEntity());
    when(mockUserRepository.findAllById(userIdList))
        .thenReturn(userEntityList);

    final var contactList = userContactService.findUserContactsByIds(userIdList);

    final var contact = contactList.get(0);
    final var testUserEntity = userEntityList.get(0);

    assertEquals(contactList.size(), 2);
    assertEquals(contact.firstName(), testUserEntity.getFirstName());
    assertEquals(contact.lastName(), testUserEntity.getLastName());
    assertEquals(contact.email(), testUserEntity.getContact().getEmail());
    assertEquals(contact.phoneNumber(), testUserEntity.getContact().getPhoneNumber());
    assertEquals(contact.address().houseNumber(), testUserEntity.getContact().getAddress().getHouseNumber());
    assertEquals(contact.address().streetName(), testUserEntity.getContact().getAddress().getStreetName());
    assertEquals(contact.address().postCode(), testUserEntity.getContact().getAddress().getPostCode());
    assertEquals(contact.address().postCode(), testUserEntity.getContact().getAddress().getPostCode());
    assertEquals(contact.address().city(), testUserEntity.getContact().getAddress().getCity());
    assertEquals(contact.address().state(), testUserEntity.getContact().getAddress().getState());
    assertEquals(contact.address().country(), testUserEntity.getContact().getAddress().getCountry());

    verify(mockUserRepository, times(1)).findAllById(userIdList);
  }

  @Test
  @DisplayName("When findUserContactsByIds is invoked with invalid list of IDs then return empty list")
  void whenFindUserContactsByIdsIsInvokedThenReturnEmptyList() {
    final var userIdList = List.of(100L, 200L);
    when(mockUserRepository.findAllById(userIdList))
        .thenReturn(List.of());

    final var contactList = userContactService.findUserContactsByIds(userIdList);
    assertEquals(contactList.size(), 0);

    verify(mockUserRepository, times(1)).findAllById(userIdList);
  }
}