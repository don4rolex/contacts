package com.andrew.contacts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author andrew
 */
@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @JoinColumn(name = "house_number", nullable = false)
  private String houseNumber;

  @JoinColumn(name = "street_name", nullable = false)
  private String streetName;

  @JoinColumn(name = "post_code", nullable = false)
  private String postCode;

  @JoinColumn(name = "city", nullable = false)
  private String city;

  @JoinColumn(name = "state", nullable = false)
  private String state;

  @JoinColumn(name = "country", nullable = false)
  private String country;

  public Address() {
  }

  public Address(String houseNumber, String streetName, String postCode, String city, String state, String country) {
    this.houseNumber = houseNumber;
    this.streetName = streetName;
    this.postCode = postCode;
    this.city = city;
    this.state = state;
    this.country = country;
  }

  public Long getId() {
    return id;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public String getStreetName() {
    return streetName;
  }

  public String getPostCode() {
    return postCode;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }
}
