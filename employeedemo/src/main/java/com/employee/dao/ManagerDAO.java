package com.employee.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employee.Employee;

import com.employee.Manager;

public class ManagerDAO {

	private Session session;

	public ManagerDAO(Session session) {
		super();
		this.session = session;
	}

	public void saveManager(Manager manager) {

		Transaction tx = null;

		try {
			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			// save the manager details
			session.save(manager);
			tx.commit();

			System.out.println("Manager details added successfully!");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); //
			}
			e.printStackTrace();
		}
	}

	public void assignEmployeeToManager(int employeeId, int managerId) {
		Transaction tx = null;

		try {

			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			Employee employee = session.get(Employee.class, employeeId);
			Manager manager = session.get(Manager.class, managerId);

			employee.setManager(manager);
			manager.getEmployees();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Manager> findManagerByEmployeeId(int employeeId) {

		try {

			Manager manager = session.createQuery("from Manager where employeeId= :employeeId", Manager.class)
					.setParameter("employeeId", employeeId).getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Employee> findAllEmployeeByManagerId(int managerId) {
       
		try {
		Manager manager = session.get(Manager.class, managerId);
		
		if(manager !=null) {
			return manager.getEmployees();
		}
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
		return null;

	// method to see which manager is assigned to an employee using employee id
		public Manager findManagerForEmployee(String empId) {
			Manager manager = session.createQuery("select e.manager from Employee e " + "where id=:employeeId", Manager.class)
					.setParameter("employeeId", 2).getSingleResult();

			return manager;
		}

	// method to see list of employees working under the manager
		public List<Employee> findEmpsWorkingUnderManager(String managerId) {
			Manager manager = session.get(Manager.class, managerId);
			List<Employee> employees = session.createQuery("from Employee where manager = :man", Employee.class)
					.setParameter("man", manager).getResultList();

			return employees;
		}
}
