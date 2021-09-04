package com.oms.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(	name = "emergencyContact")
@Data
public class EmergencyContact {

	/*Personal Data*/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="last_name")
	private String lastName;

	@Column(name="first_name")
	private String firstName;

	@Column(name="gender")
	private char gender;

	@Column(name="phone1", unique=true)
	private String phone1;

	@Column(name="phone2", nullable = true)
	private String phone2;

	@Column(name="email", unique=true)
	private String email;

	@Column(name="nature")
	private String nature;

	/*Address*/
	@Column(name="address")
	private String address;
}
