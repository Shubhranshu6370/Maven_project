package com.employee;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Manager {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	
	@Column(name = "manager_name", length = 30)
	private String managerName;
	
	@Column(name = "manager_email", length = 40, unique = true)
	private String managerEmail;
    
	//establish One to Many relationship - unidirectional
	@OneToMany
	@JoinColumn(name="manager_id")
	private List<Employee> employees; 
	
	public Manager(String managerName, String managerEmail) {
		super();
		this.managerName = managerName;
		this.managerEmail = managerEmail;
	}
	
	
}
