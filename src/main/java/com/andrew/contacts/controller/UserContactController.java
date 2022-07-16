package com.andrew.contacts.controller;

import com.andrew.contacts.dto.UserContactDto;
import com.andrew.contacts.service.UserContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author andrew
 */
@RestController
public class UserContactController {

  private final UserContactService userContactService;

  public UserContactController(UserContactService userContactService) {
    this.userContactService = userContactService;
  }

  @PostMapping("/contact")
  public UserContactDto addUserContact(@RequestBody UserContactDto dto) {
    return userContactService.addUserContact(dto);
  }

  @GetMapping("/contact/{id}")
  public UserContactDto findUserContactById(@PathVariable("id") Long id) {
    return userContactService.findUserContactById(id);
  }

  @PostMapping("/contacts")
  public List<UserContactDto> findUserContactsByIds(@RequestBody List<Long> ids) {
    return userContactService.findUserContactsByIds(ids);
  }
}
