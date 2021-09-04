package com.oms.payload.request;

import com.oms.model.EmergencyContact;
import lombok.Data;

import java.util.Set;
@Data
public class CreationRequest {
  private Long id;
  private String firstName;
  private String lastName;
  private char gender;
  private String birthday;
  private String businessPhone;
  private String privatePhone;
  private String email;
  private String nationality;
  private Boolean status;
  private String linkedinLink;
  private String xingLink;
  private String facebookLink;
  private String youtubeLink;
  private String address;
  private String city;
  private String country;
  private String jobType;
  private String department;
  private String jobRole;
  private String workLocation;
  private String password;
  private Set<EmergencyContact> emergencyContacts;
  private Set<String> role;

 /* public CreationRequest(String firstName, String lastName, char gender, String birthday, String businessPhone, String privatePhone, String email, String nationality, Boolean status, String linkedinLink, String xingLink, String facebookLink, String youtubeLink, String address, String city, String country, Set<Institution> institution, String jobType, String department, String jobRole, String workLocation, String password, Set<String> role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.birthday = birthday;
    this.businessPhone = businessPhone;
    this.privatePhone = privatePhone;
    this.email = email;
    this.nationality = nationality;
    this.status = status;
    this.linkedinLink = linkedinLink;
    this.xingLink = xingLink;
    this.facebookLink = facebookLink;
    this.youtubeLink = youtubeLink;
    this.address = address;
    this.city = city;
    this.country = country;
    this.jobType = jobType;
    this.department = department;
    this.jobRole = jobRole;
    this.workLocation = workLocation;
    this.password = password;
    this.role = role;
  }*/
}
