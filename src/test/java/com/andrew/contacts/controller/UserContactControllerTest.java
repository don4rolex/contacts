package com.andrew.contacts.controller;

import com.andrew.contacts.dto.UserContactDto;
import com.andrew.contacts.exception.UserNotFoundException;
import com.andrew.contacts.service.UserContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.andrew.contacts.testfixture.TestFixture.testUserContactDto;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author andrew
 */
@AutoConfigureMockMvc
@WebMvcTest(UserContactController.class)
class UserContactControllerTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserContactService userContactService;

  @Test
  @DisplayName("When add user contact request is received then return 200 OK")
  void whenAddContactRequestIsReceivedThen200OK() throws Exception {
    final var userContact = testUserContactDto();
    when(userContactService.addUserContact(any(UserContactDto.class)))
        .thenReturn(userContact);

    mockMvc.perform(post("/contact")
            .content(OBJECT_MAPPER.writeValueAsString(userContact))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(userContact.userId()))
        .andExpect(jsonPath("$.firstName").value(userContact.firstName()))
        .andExpect(jsonPath("$.lastName").value(userContact.lastName()))
        .andExpect(jsonPath("$.email").value(userContact.email()))
        .andExpect(jsonPath("$.address.houseNumber").value(userContact.address().houseNumber()))
        .andExpect(jsonPath("$.address.streetName").value(userContact.address().streetName()))
        .andExpect(jsonPath("$.address.postCode").value(userContact.address().postCode()))
        .andExpect(jsonPath("$.address.city").value(userContact.address().city()))
        .andExpect(jsonPath("$.address.state").value(userContact.address().state()))
        .andExpect(jsonPath("$.address.country").value(userContact.address().country()));

    verify(userContactService, times(1)).addUserContact(any(UserContactDto.class));
  }

  @Test
  @DisplayName("When valid user ID is received then return user contact")
  void whenValidIdIsReceivedThenReturnUserContact() throws Exception {
    final var userContact = testUserContactDto();
    when(userContactService.findUserContactById(1L))
        .thenReturn(userContact);

    mockMvc.perform(get("/contact/1")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(userContact.userId()))
        .andExpect(jsonPath("$.firstName").value(userContact.firstName()))
        .andExpect(jsonPath("$.lastName").value(userContact.lastName()))
        .andExpect(jsonPath("$.email").value(userContact.email()))
        .andExpect(jsonPath("$.address.houseNumber").value(userContact.address().houseNumber()))
        .andExpect(jsonPath("$.address.streetName").value(userContact.address().streetName()))
        .andExpect(jsonPath("$.address.postCode").value(userContact.address().postCode()))
        .andExpect(jsonPath("$.address.city").value(userContact.address().city()))
        .andExpect(jsonPath("$.address.state").value(userContact.address().state()))
        .andExpect(jsonPath("$.address.country").value(userContact.address().country()));

    verify(userContactService, times(1)).findUserContactById(1L);
  }

  @Test
  @DisplayName("When user ID is invalid return 404 - Not found")
  void whenUserIdIsInvalidThenReturn404NotFound() throws Exception {
    final var invalidId = 1L;
    when(userContactService.findUserContactById(invalidId))
        .thenThrow(UserNotFoundException.class);

    mockMvc.perform(get("/contact/1")
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(userContactService, times(1)).findUserContactById(invalidId);
  }

  @Test
  @DisplayName("When valid list of user IDs is received then return list of user contacts")
  void whenValidIdsIsReceivedThenReturnUserContacts() throws Exception {
    final var userIdList = List.of(1L, 2L);
    final var contactList = List.of(testUserContactDto(), testUserContactDto());
    when(userContactService.findUserContactsByIds(userIdList))
        .thenReturn(contactList);

    final var userContact = contactList.get(0);
    mockMvc.perform(post("/contacts")
            .content(OBJECT_MAPPER.writeValueAsString(userIdList))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].userId").value(userContact.userId()))
        .andExpect(jsonPath("$[0].firstName").value(userContact.firstName()))
        .andExpect(jsonPath("$[0].lastName").value(userContact.lastName()))
        .andExpect(jsonPath("$[0].email").value(userContact.email()))
        .andExpect(jsonPath("$[0].address.houseNumber").value(userContact.address().houseNumber()))
        .andExpect(jsonPath("$[0].address.streetName").value(userContact.address().streetName()))
        .andExpect(jsonPath("$[0].address.postCode").value(userContact.address().postCode()))
        .andExpect(jsonPath("$[0].address.city").value(userContact.address().city()))
        .andExpect(jsonPath("$[0].address.state").value(userContact.address().state()))
        .andExpect(jsonPath("$[0].address.country").value(userContact.address().country()));

    verify(userContactService, times(1)).findUserContactsByIds(userIdList);
  }

  @Test
  @DisplayName("When invalid list of user IDs is received then return empty list of user contacts")
  void whenInvalidIdsIsReceivedThenReturnEmpty() throws Exception {
    final var idList = List.of(100L, 101L);
    when(userContactService.findUserContactsByIds(idList))
        .thenReturn(List.of());

    mockMvc.perform(post("/contacts")
            .content(OBJECT_MAPPER.writeValueAsString(idList))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));

    verify(userContactService, times(1)).findUserContactsByIds(idList);
  }
}