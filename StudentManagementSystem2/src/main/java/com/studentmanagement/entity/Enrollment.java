package com.studentmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Enrollment {

	@Id
	@Column(length = 10)
	private String enrollId;

	@Column(name = "enrolled_date")
	private LocalDate dateOfEnrollment;

	// establishing many to one relationship with Student
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	// establishing many to one relationship with Course
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	// establishing many to one relationship with Instructor
	@ManyToOne
	@JoinColumn(name = "ins_id")
	private Instructor instructor;

	public Enrollment(LocalDate dateOfEnrollment, Student student, Course course, Instructor instructor) {
		super();
		this.dateOfEnrollment = dateOfEnrollment;
		this.student = student;
		this.course = course;
		this.instructor = instructor;
	}
	
	
}
