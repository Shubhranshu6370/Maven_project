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
public class Score {

	@Id
	@Column(length = 10)
	private String scoreId;
	
	@Column(nullable = false)
	private LocalDate dateOfExam;
	
	@Column(length = 10, nullable = false)
	private String creditObtained;

	// establishing many to one relationship with Course
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	// establishing many to one relationship with Student
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Score(LocalDate dateOfExam, String creditObtained, Course course, Student student) {
		super();
		this.dateOfExam = dateOfExam;
		this.creditObtained = creditObtained;
		this.course = course;
		this.student = student;
	}
	
	
}
