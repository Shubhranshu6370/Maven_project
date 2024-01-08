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
public class Course {

	@Id
	@Column(length = 10)
	private String courseId;
	
	@Column(length = 30, nullable = false)
	private String courseTitle;
	
	@Column(nullable = false)
	private int credits;
	
	@OneToMany
	@JoinColumn(name = "course_id")
	private List<Enrollment> enrollments;
	
	@OneToMany
	@JoinColumn(name = "course_id")
	private List<Score> scores;

	public Course(String courseTitle, int credits) {
		super();
		this.courseTitle = courseTitle;
		this.credits = credits;
	}
	
	
	
}