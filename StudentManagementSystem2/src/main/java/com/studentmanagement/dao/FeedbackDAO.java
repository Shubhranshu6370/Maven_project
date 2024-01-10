package com.studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.studentmanagement.entity.Feedback;

public class FeedbackDAO {
	Session session = null;

	public FeedbackDAO(final Session session) {
		this.session = session;
	}

	// method to save Feedback details
	public Feedback saveFeedback(Feedback feed) {
		// adding the new Feedback details using session object
		try {
			session.beginTransaction();

			// save the Feedback object
			session.save(feed);
			// commit the changes
			session.getTransaction().commit();
			session.clear();

			return feed;
		} catch (HibernateException e) {
			System.out.println("Hibernate Exception: " + e);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return null;
	}

	// method to fetch all Feedback details
	public List<Feedback> findAllFeedbacks() {
		List<Feedback> feedbacks = session.createQuery("from Feedback", Feedback.class).getResultList();
		return feedbacks;
	}

	// method to fetch Feedback details using primary key id
	public Feedback findFeedbackById(String feedId) {
		// if object not found returns null
		Feedback feed = session.get(Feedback.class, feedId);

		if (feed != null) {
			return feed;
		} else
			System.out.println("Feedback details not found!");

		return null;

	}
	
	
	//method to fetch feedback details using instructor name
	public List<Feedback> getFeedbackByInsName(String insName)
	{
		List<Feedback> feedbacks = session.createQuery("from Feedback where instructorName = :name", Feedback.class)
		.setParameter("name", insName).getResultList();
		return feedbacks;
	}
	
	
	//method to fetch the list of instructors for which a specific student has given feedback
	public void findListOfTeachers(String stdId)
	{
		List<Feedback> feedbacks = session.createQuery("from Feedback where student.studentId= :sId", Feedback.class)
				.setParameter("sId", stdId).getResultList();
		
		for(Feedback feed: feedbacks)
		{
			String insName = feed.getInstructorName();
			System.out.println(insName);
		}
		
	}
	
	//method to see the feedback of a specific teacher for a secific student
	public void findFeedbackForSpecificInsByStudent(String stdId, String insName)
	{
		List<Feedback> feedbacks = session.createQuery("from Feedback where student.studentId= :sId and "
				+ "instructorName = :name", Feedback.class)
				.setParameter("sId", stdId)
				.setParameter("name", insName)
				.getResultList();
		
		System.out.println("Feedback given for instructor: "+insName+" by student with id "+stdId);
		for(Feedback feed: feedbacks)
		{
			String feedback = feed.getFeedback();	
			System.out.println(feedback);
		}
	}
}
