package com.studentmanagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Instructor {

	@Id
	@Column(length=10)
	private String insId;
	
	@Column(length = 30, nullable = false)
	private String firstName;
	
	@Column(length = 30, nullable = false)
	private String lastName;
	
	@Column(length = 30, nullable = false, unique = true)
	private String email;
	
	@OneToMany
	@JoinColumn(name = "ins_id")
	private List<Enrollment> enrollments;

	public Instructor(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}
