package com.andrew.contacts.service;

import com.andrew.contacts.dto.UserContactDto;
import com.andrew.contacts.exception.UserNotFoundException;
import com.andrew.contacts.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.andrew.contacts.service.UserContactMapper.mapToUserContactDto;
import static com.andrew.contacts.service.UserContactMapper.mapToUserEntity;
import static java.util.stream.Collectors.toList;

/**
 * @author andrew
 */
@Service
public class UserContactService {

  private final UserRepository userRepository;

  public UserContactService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserContactDto addUserContact(UserContactDto dto) {
    final var user = userRepository.save(mapToUserEntity(dto));
    return mapToUserContactDto(user);
  }

  public UserContactDto findUserContactById(Long id) {
    final var user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User %d not found".formatted(id)));
    return mapToUserContactDto(user);
  }

  public List<UserContactDto> findUserContactsByIds(List<Long> ids) {
    return userRepository.findAllById(ids)
        .stream()
        .map(UserContactMapper::mapToUserContactDto)
        .collect(toList());
  }
}
