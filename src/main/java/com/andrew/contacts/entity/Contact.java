package com.andrew.contacts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author andrew
 */
@Entity
@Table(name = "contact")
public class Contact {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @JoinColumn(name = "email", nullable = false)
  private String email;

  @JoinColumn(name = "phone_number", nullable = false)
  private String phoneNumber;

  @OneToOne(cascade = ALL)
  @JoinColumn(name = "address_id", nullable = false)
  private Address address;

  public Contact() {
  }

  public Contact(String email, String phoneNumber, Address address) {
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Address getAddress() {
    return address;
  }
}
