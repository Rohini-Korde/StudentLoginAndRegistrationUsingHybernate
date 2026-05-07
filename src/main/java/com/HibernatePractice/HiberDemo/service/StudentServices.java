package com.HibernatePractice.HiberDemo.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.HibernatePractice.HiberDemo.model.Student;
import com.HibernatePractice.HiberDemo.util.HibernateUtil;

public class StudentServices {
	
	public static void addStudent(String name, String city) {

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    Student s = new Student();
	    s.setName(name);
	    s.setCity(city);

	    session.save(s);

	    tx.commit();
	    session.close();

	    System.out.println("Student Added.");
	}
	
	
	public static void viewStudents() {

	    Session session = HibernateUtil.getSessionFactory().openSession();

	    Query<Student> query = session.createQuery("from Student", Student.class);

	    for (Student s : query.list()) {
	        System.out.println(s.getId() + " " + s.getName() + " " + s.getCity());
	    }

	    session.close();
	}
	
	
	public static void updateStudent(int id, String city) {

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    Student s = session.get(Student.class, id);

	    if (s != null) {
	        s.setCity(city);
	        session.update(s);
	        System.out.println("Updated.");
	    } else {
	        System.out.println("Student not found.");
	    }

	    tx.commit();
	    session.close();
	}
	
	
	public static void deleteStudent(int id) {

	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();

	    Student s = session.get(Student.class, id);

	    if (s != null) {
	        session.delete(s);
	        System.out.println("Deleted.");
	    } else {
	        System.out.println("Student not found.");
	    }

	    tx.commit();
	    session.close();
	}

}
