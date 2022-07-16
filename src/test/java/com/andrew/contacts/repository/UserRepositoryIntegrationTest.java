package com.andrew.contacts.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.andrew.contacts.testfixture.TestFixture.testUserEntity;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author andrew
 */
@DataJpaTest
@ExtendWith(value = SpringExtension.class)
class UserRepositoryIntegrationTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("When userRepository.save() is invoked then cascade save address and contact entities")
  void saveUser() {
    final var testUserEntity = testUserEntity();

    //Verify that IDs are null before saving
    assertNull(testUserEntity.getId());
    assertNull(testUserEntity.getContact().getId());
    assertNull(testUserEntity.getContact().getAddress().getId());

    final var savedUser = userRepository.save(testUserEntity);

    //Verify that IDs are NOT null after saving
    assertNotNull(savedUser.getId());
    assertNotNull(savedUser.getContact().getId());
    assertNotNull(savedUser.getContact().getAddress().getId());
  }
}