package com.oms.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="students")
public class Student {
	/*Personal Data*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code")
	private Long code;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="gender")
	private char gender;

	@Column(name="birthday")
	private String birthday;

	@Column(name="nationality")
	private String nationality;

	@Column(name="identification_number", unique=true)
	private String identificationNumber;

	/*Address*/

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;

	/*Emergency Contact*/

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "student_emmergency",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "emmergency_id"))
	private Set<EmergencyContact> emergencyContacts = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	//@JsonBackReference
	private Room room;


	public Student() {
	}

	public Student(String firstName, String lastName, char gender, String birthday, String nationality, String identificationNumber, String address, String city,Room room) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.nationality = nationality;
		this.identificationNumber = identificationNumber;
		this.address = address;
		this.city = city;
		this.room=room;
	}
}
