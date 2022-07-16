package com.andrew.contacts.exception;

/**
 * @author andrew
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
