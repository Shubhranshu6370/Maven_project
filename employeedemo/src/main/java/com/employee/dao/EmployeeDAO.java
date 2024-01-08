package com.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employee.Employee;

public class EmployeeDAO {

	private Session  session;

	public EmployeeDAO(Session session) {
		super();
		this.session = session;
	}

	// create a method to save employee details
	public void saveEmployee(Employee employee) {

		Transaction tx = null;

		try {
			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			// save the employee details
			session.save(employee);
			tx.commit();

			System.out.println("Employee details added successfully!");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); //
			}
			e.printStackTrace();
		}
	}

	// create a method to find the employee details
	public Employee findEmployeeById(Long id) {
		Employee emp = session.get(Employee.class, id);

		if (emp != null) {
			return emp;
		} else {
			System.out.println("Employee details not found!");
			return null;
		}
	}

	// create a method to find all employee details
	public List<Employee> findAllEmployees() {
		List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
		return employees;
	}

	// create a method employee details by id
	public void updateEmpById(Long id, Employee emp) {
		Transaction tx = null;

		try {
			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			// fetching the existing Employee details
			Employee employee = session.get(Employee.class, id);

			if (employee != null) {
				employee.setFirstName(emp.getFirstName());
				employee.setLastName(emp.getLastName());
				employee.setSalary(emp.getSalary());

				// update the Employee details
				session.saveOrUpdate(tx); // merge the changes
				tx.commit();
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

	}

	// create a method to remove the employee
	public void removeEmployeeById(long id) {

		Transaction tx = null;

		try {
			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			Employee employee = session.get(Employee.class, id);

			if (employee != null) {
				session.delete(employee);
				tx.commit();
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	// create a method to find employee by firstname
	public Employee findByfirstname(String firstName) {
		try {
			Employee emp = session.createQuery("select e from Employee e where e.firstName = :em", Employee.class)
					.setParameter("em", firstName).getSingleResult();
			return emp;

		} catch (Exception e) {
			System.out.println("Employee details not found!!");
		}
		return null;

	}

}
