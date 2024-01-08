package com.studentmanagement;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.studentmanagement.dao.FeedbackDAO;
import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.entity.Feedback;
import com.studentmanagement.entity.Student;

public class StudentApp {

	public static void main(String[] args) {
		
		try
		{
//			Configuration cfg = new Configuration();
//			SessionFactory factory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
//			Session session = factory.openSession();
			
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = factory.openSession();
			
			StudentDAO studentDao = new StudentDAO(session);
			FeedbackDAO feedbackDAO = new FeedbackDAO(session);
			
			//add new student details
//			Student std1 = new Student("Rohit", "Dey", "rohit@gmail.com", "Male", "9864578962",
//			LocalDate.parse("2003-01-07"));
//			Student std2 = new Student("Deeptimayee", "Kisan", "deeptimayee@gmail.com", "Female", "7201563248",
//			LocalDate.parse("2002-04-17"));
//			
//			studentDao.saveStudent(std1);
//			studentDao.saveStudent(std2);
//			
//			//add some feedbacks
//			Feedback feed1 = new Feedback(LocalDate.now(), "John Doe", "Session was good", std1);
//			Feedback feed2 = new Feedback(LocalDate.now(), "Derek Smith", "Properly explained", std1);
//			Feedback feed3 = new Feedback(LocalDate.now(), "John Doe", "Properly explained", std2);
//			
//			feedbackDAO.saveFeedback(feed1);
//			feedbackDAO.saveFeedback(feed2);
//			feedbackDAO.saveFeedback(feed3);
//			
//			//fetch the list of teachers for which a specific student has given feedback
//			System.out.println("List of teachers for which a specific student has given feedback:");
//			String studentId = "S101";
//			System.out.println();
//			feedbackDAO.findListOfTeachers(studentId);
//			
//			System.out.println();
//			
//			//fetch the feedback recevied by an instructor given by a student	
//			feedbackDAO.findFeedbackForSpecificInsByStudent(studentId, "Derek Smith");
//	
//			
//			//calling the method to fetch the list of students who have provided feedbacks
//			studentDao.getStudentsWithFeedback();
			
			studentDao.removeStdById("S101");
			
			
		}
		catch(HibernateException e)
		{
			System.out.println("Hibernate Exception:");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception:");
			e.printStackTrace();
		}

	}

}

/*
  System.out.println("Student details using email:");
			Student stdByEmail = studentDao.findByEmail("rohit@gmail.com");
			System.out.println(stdByEmail);
			
			System.out.println("****************************************************");
			
			System.out.println();
			
			System.out.println("Updating student details using id:");
			Student newStd = new Student("Rohit", "Dey", "rohitdey@gmail.com", "Male", "9864512345",
					LocalDate.parse("2003-01-07"));
			
			String updateId = "S101";
			studentDao.updateStdById(updateId, newStd);
			Student updatedStd = studentDao.findStudentById(updateId);
			System.out.println("Updated student details");
			System.out.println(updatedStd);
			
			System.out.println("****************************************************");
			
			System.out.println();
			System.out.println("Removing student details with id S102");
			studentDao.removeStdById("S102");
			
			System.out.println("****************************************************");
			
			System.out.println();
			System.out.println("Removing all students records");
			studentDao.removeAllStudents();
  */


////add student details
//Student std1 = new Student("S101", "Rohit", "Dey", "rohit@gmail.com", "Male", "9864578962",
//LocalDate.parse("2003-01-07"));
//Student std2 = new Student("S102", "Deeptimayee", "Kisan", "deeptimayee@gmail.com", "Female", "7201563248",
//LocalDate.parse("2002-04-17"));
//
//studentDao.saveStudent(std1);
//studentDao.saveStudent(std2);
//
//Student stdById = studentDao.findStudentById("S101");
//System.out.println("Student: "+stdById);
//
//System.out.println("****************************************************");
//
//System.out.println();
//
//System.out.println("List of students:");
//List<Student> students = studentDao.findAllStudents();
//for(Student std : students)
//{
//	System.out.println(std);
//}
//System.out.println("****************************************************");
//
//System.out.println();
//
////add some feedbacks
//Feedback feed1 = new Feedback(LocalDate.now(), "John Doe", "Session was good", std1);
//Feedback feed2 = new Feedback(LocalDate.now(), "Derek Smith", "Properly explained", std1);
//Feedback feed3 = new Feedback(LocalDate.now(), "John Doe", "Properly explained", std2);
//
//feedbackDAO.saveFeedback(feed1);
//feedbackDAO.saveFeedback(feed2);
//feedbackDAO.saveFeedback(feed3);
//
////list of feedbacks
//List<Feedback> feedbacks = feedbackDAO.findAllFeedbacks();
//for(Feedback feed : feedbacks)
//{
//	System.out.println(feed);
//}
//System.out.println("****************************************************");
//
//System.out.println();
//
////fetching feedback using instructor Name
//String insName = "John Doe";
//System.out.println("Feedbacks for instructor: "+insName);
//List<Feedback> result = feedbackDAO.getFeedbackByInsName(insName);
//for(Feedback feed : result)
//{
//	System.out.println(feed);
//}
//System.out.println("****************************************************");
//
//System.out.println();





