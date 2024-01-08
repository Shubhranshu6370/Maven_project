package com.studentmanagement;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.studentmanagement.dao.FeedbackDAO;
import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.entity.Feedback;
import com.studentmanagement.entity.Student;

public class App 
{
    public static void main( String[] args )
    {
    	try
		{
    		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = factory.openSession();
			
			StudentDAO studentDao = new StudentDAO(session);
			FeedbackDAO feedbackDAO = new FeedbackDAO(session);
			
			Scanner sc = new Scanner(System.in);
			
//			System.out.println("Enter student id:");
//			String stdId = sc.next();
//			
//			sc.nextLine();
//			System.out.println("Enter the instructor name: ");
//			String insName = sc.nextLine();
//			
//			System.out.println("Enter the feedback: ");
//			String feedback = sc.nextLine();
//
//			Student std = studentDao.findStudentById(stdId);
//			
//			Feedback feed3 = new Feedback(LocalDate.now(), insName, feedback, std);
//			
//			feedbackDAO.saveFeedback(feed3);
			
			studentDao.fetchLastAddedId();
			
			Student std = new Student("Bijaylaxmi", "Oram", "bijaylaxmi@gmail.com", "Female", "9861272135",
					LocalDate.parse("2002-07-07"));
			
			studentDao.saveStudent(std);
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
