package com.studentmanagement.dao;

import java.util.List;


import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.studentmanagement.entity.Feedback;
import com.studentmanagement.entity.Student;

public class StudentDAO {
	Session session = null;

	public StudentDAO(final Session session) {
		this.session = session;
	}
	
	//method to fetch the last added id
	public String fetchLastAddedId()
	{
		Object stdId = session.createQuery("select max(s.studentId) from Student s").getSingleResult();
		return String.valueOf(stdId);
	}

	// method to save Student details
	public Student saveStudent(Student std) {
		// adding the new student details using session object
		try {
			session.beginTransaction();

			//to generate the custom id
			String stdId = fetchLastAddedId();
			
			if(stdId.contains("null"))
			{
				stdId = "S100";
			}
			
			String prefix = stdId.substring(0, 1); //S
			int postfix = Integer.parseInt(stdId.substring(1)); //102
			String newId = prefix + (postfix+1); //S + (102+1) = S + 103 = S103
			std.setStudentId(newId);
			
			// save the student object
			session.save(std);
			// commit the changes
			session.getTransaction().commit();
			System.out.println("Student details saved successfully!");
			session.clear();

			return std;
		} catch (HibernateException e) {
			System.out.println("Hibernate Exception: " + e);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return null;
	}

	// method to fetch all student details
	public List<Student> findAllStudents() {
		List<Student> students = session.createQuery("from Student", Student.class).getResultList();
		return students;
	}

	// method to fetch student details using primary key id
	public Student findStudentById(String stdId) {
		// if object not found returns null
		Student std = session.get(Student.class, stdId);

		if (std != null) {
			return std;
		} else
			System.out.println("Student details not found!");

		return null;

	}

	// method to fetch student details using email
	public Student findByEmail(String stdEmail) {
		try {
//				Student std = session.createQuery("select s from Student s where s.sessionail = :session", Student.class)
//						.setParameter("session", stdsessionail)
//						.getSingleResult();
			Student std = session.createQuery("from Student where email = :em", Student.class)
					.setParameter("em", stdEmail).getSingleResult();

			return std;
		} catch (NoResultException e) {
			System.out.println("Student details not found!!");
		}

		return null;
	}

	// method to update student details
	public void updateStdById(String stdId, Student std) {
		Transaction tx = null;

		try {
			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			// fetching the existing student details
			// using load if the object is not found it will throw an exception
			// ObjectNotFoundException
			Student existStd = session.load(Student.class, stdId);

			if (existStd != null) {
				existStd.setFirstName(std.getFirstName());
				existStd.setLastName(std.getLastName());
				existStd.setEmail(std.getEmail());
				existStd.setDateOfBirth(std.getDateOfBirth());
				existStd.setGender(std.getGender());
				existStd.setPhone(std.getPhone());

				// update the student details
				session.saveOrUpdate(existStd); // updating the changes
				tx.commit();
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	// method to delete/rsessionove student details using id from the database
	public void removeStdById(String stdId) {
		Transaction tx = null;

		Student std = session.find(Student.class, stdId);

		try {
			tx = session.getTransaction();

			if (!tx.isActive()) {
				tx.begin();
			}

			if (std != null) {
				// remove the student object
				session.delete(std);
				System.out.println("Student with id " + stdId + " removed successfully!");
				tx.commit();
			} else
				System.out.println("Student details not found!");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	// method to remove all student details from the database
	public void removeAllStudents() {
		List<Student> students = findAllStudents();

		for (Student std : students) {
			Transaction tx = null;
			try {
				tx = session.getTransaction();

				if (!tx.isActive()) {
					tx.begin();
				}

				session.delete(std);
				tx.commit();

			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			}
		}
		System.out.println("All student records removed successfully!");
	}

	// method to fetch the student details who have given feedbacks
	public void getStudentsWithFeedback() {
		String hql = "SELECT f FROM Feedback f LEFT JOIN Student s ON f.student = s.studentId ";
		List<Feedback> feedbacks = session.createQuery(hql, Feedback.class).getResultList();

		System.out.println("Student Id\tFirst Name\tInstructorName\tFeedback given");
		for (Feedback feed : feedbacks) {
			System.out.println(feed.getStudent().getStudentId() + "\t\t" + feed.getStudent().getFirstName() + "\t\t"
					+ feed.getInstructorName() + "\t" + feed.getFeedback());
		}
	}

}
