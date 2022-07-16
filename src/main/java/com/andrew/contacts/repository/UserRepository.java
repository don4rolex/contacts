package com.andrew.contacts.repository;

import com.andrew.contacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author andrew
 */
public interface UserRepository extends JpaRepository<User, Long> {
  
}
