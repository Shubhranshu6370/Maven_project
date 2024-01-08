package com.employeemanagement;

import java.time.LocalDate;


import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.employee.dao.EmployeeDAO;
import com.employee.dao.ManagerDAO;
import com.employee.Employee;
import com.employee.Manager;

public class EmployeeApp {

	public static void main(String[] args) {
        
		
		try {
			
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = factory.openSession();
            
            EmployeeDAO employeeDao = new EmployeeDAO(session);

			Employee emp1 = new Employee("Leo", "Das", 27000);
			Employee emp2 = new Employee("Ankit", "Nayak", 19000);
			Employee emp3 = new Employee("Johnsan","Doe",30000);
			
            //To save employee details
			employeeDao.saveEmployee(emp1);
			employeeDao.saveEmployee(emp2);
			employeeDao.saveEmployee(emp3);
			
			// Print the employee details
			Employee empById = employeeDao.findEmployeeById(1L);
			System.out.println("Employee: " + empById);


			Employee empById2 = employeeDao.findEmployeeById(2L);
			System.out.println("Employee: " + empById2);
			
			Employee empById3 = employeeDao.findEmployeeById(3L);
			System.out.println("Employee: " + empById3);

			System.out.println();
			
			ManagerDAO managerDao = new ManagerDAO(session);
			
			Manager mag1 = new Manager("Sachin Singh","sachin@gmail.com");
			Manager mag2 = new Manager("Tanshik Chowdhury","tanisk@gmail.com");
			Manager mag3 = new Manager("Elvish Singhania","elvish@gmail.com");
		
			managerDao.saveManager(mag1);
			managerDao.saveManager(mag2);
			managerDao.saveManager(mag3);
			
//			Manager magById = managerDao.findManagerByEmployeeId(1);
//			System.out.println("Manager: "+magById);
//			
//			Manager magById2 = managerDao.findManagerByEmployeeId(2);
//			System.out.println("Manager: "+magById2);
//			
//			Manager magById3 = managerDao.findManagerByEmployeeId(3);
//			System.out.println("Manager: "+magById3);
//			
//			System.out.println("List of Managers:");
//			
//			Manager managers = managerDao.findManagerByEmployeeId(3);
//			
//			
			System.out.println("List of Employees:");
			
			//To fetch all employee details
			List<Employee> employees = employeeDao.findAllEmployees();
			for(Employee emp : employees)
			 {
				 System.out.println(emp);
			 }
			
			System.out.println("******************************************************");
			
			System.out.println();
		
			 
			
			 System.out.println("***********************************************************");
			 
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}




////To update employee detail by using id
//System.out.println("Updating employee details using id:");
//Employee newemp = new Employee("Raghab","Jurel",28000);
//
//Long updateId = 1L;
//employeeDao.updateEmpById(updateId, newemp);
//Employee updateemp= employeeDao.findEmployeeById(updateId);
//System.out.println("Update employee details");
//System.out.println(updateemp);
//
//System.out.println("*******************************************************");
//
//System.out.println();
//
////Removing employee detail using id
//System.out.println("Removing employee details with id:");
//employeeDao.removeEmployeeById(2L);
//System.out.println("Remove  employee records!!");
//
//System.out.println("********************************************************");
//
//System.out.println();
//
////Fetch employee details using firstname
//System.out.println(" fetching Employee details using firstname:");
//
//Employee empId = employeeDao.findByfirstname("Johnsan");
//System.out.println(empId);
//
//System.out.println("Employee details found!");
//
//




//---------------------------------------------------------OUTPUT--------------------------------------------------------------------------//
//Hibernate: insert into Employee (firstName, lastName, salary) values (?, ?, ?)
//Employee details added successfully!
//Hibernate: insert into Employee (firstName, lastName, salary) values (?, ?, ?)
//Employee details added successfully!

//Hibernate: insert into Employee (firstName, lastName, salary) values (?, ?, ?)
//Employee details added successfully!
//Employee: Employee(id=1, firstName=Leo, lastName=Das, salary=27000)
//Employee: Employee(id=2, firstName=Ankit, lastName=Nayak, salary=19000)
//Employee: Employee(id=3, firstName=Johnsan, lastName=Doe, salary=30000)
//
//List of Employees:
//Hibernate: select employee0_.id as id1_0_, employee0_.firstName as firstnam2_0_, employee0_.lastName as lastname3_0_, employee0_.salary as salary4_0_ from Employee employee0_
//Employee(id=1, firstName=Leo, lastName=Das, salary=27000)
//Employee(id=2, firstName=Ankit, lastName=Nayak, salary=19000)
//Employee(id=3, firstName=Johnsan, lastName=Doe, salary=30000)
//******************************************************
//
//Updating employee details using id:
//Hibernate: update Employee set firstName=?, lastName=?, salary=? where id=?
//Update employee details
//Employee(id=1, firstName=Raghab, lastName=Jurel, salary=28000)
//*******************************************************
//
//Removing employee details with id:
//Hibernate: delete from Employee where id=?
//Remove  employee records!!
//********************************************************
//
//fetching Employee details using firstname:
//Hibernate: select employee0_.id as id1_0_, employee0_.firstName as firstnam2_0_, employee0_.lastName as lastname3_0_, employee0_.salary as salary4_0_ from Employee employee0_ where employee0_.firstName=?
//Employee(id=3, firstName=Johnsan, lastName=Doe, salary=30000)
//Employee details found!
//***********************************************************