package org.example.data;

import java.text.MessageFormat;

public class ContactEntity {

  private String fullName;
  private String phoneNumber;
  private String email;

  public ContactEntity() {
  }

  public void printInfo() {
    System.out.println( "Contact info: " + getInfoContact() );
  }

  public String getInfoContact() {
    return MessageFormat.format( "{0}, {1}, {2}", getFullName(), getPhoneNumber(), getEmail());
  }

  public boolean isValidData() {
    return getFullName() != null && getPhoneNumber() != null && getEmail() != null;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
