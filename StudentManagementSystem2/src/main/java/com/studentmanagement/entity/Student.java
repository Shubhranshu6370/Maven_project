package com.studentmanagement.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="std_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

	@Id
	@Column(length=10)
	private String studentId;
	
	@Column(name="first_name", length=30, nullable=false)
	private String firstName;
	
	@Column(name="last_name", length=30, nullable=false)
	private String lastName;
	
	@Column(length=30, nullable=false, unique=true)
	private String email;
	
	@Column(length=10, nullable=false)
	private String gender;
	
	@Column(length=10, nullable=false, unique=true)
	private String phone;
	
	@Column
	private LocalDate dateOfBirth;
	
	//establish one to many unidirectional realtionship
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //one student can give many feedbacks
	@JoinColumn(name="student_id")
	private List<Feedback> feedbacks;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="student_id")
	private List<Enrollment> enrollment;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "student_id")
	private List<Score> scores;

	public Student(String firstName, String lastName, String email, String gender, String phone,
			LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
	}

	
	
	
}
