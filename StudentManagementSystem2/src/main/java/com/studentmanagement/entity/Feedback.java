package com.studentmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fId;
	
	@Column
	private LocalDate date;
	
	@Column(length = 30)
	private String instructorName;
	
	@Column(length = 100)
	private String feedback;
	
	//establish many to one relationship - bidirectional relationship
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

	public Feedback(LocalDate date, String instructorName, String feedback, Student student) {
		super();
		this.date = date;
		this.instructorName = instructorName;
		this.feedback = feedback;
		this.student = student;
	}

}
