package com.andrew.contacts.entity;

import javax.persistence.Column;
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
@Table(name = "user_table")
public class User {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @OneToOne(cascade = ALL)
  @JoinColumn(name = "contact_id", nullable = false)
  private Contact contact;

  public User() {
  }

  public User(String firstName, String lastName, Contact contact) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.contact = contact;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Contact getContact() {
    return contact;
  }
}
