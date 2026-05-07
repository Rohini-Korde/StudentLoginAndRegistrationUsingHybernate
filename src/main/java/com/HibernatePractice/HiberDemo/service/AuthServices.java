package com.HibernatePractice.HiberDemo.service;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.HibernatePractice.HiberDemo.model.User;
import com.HibernatePractice.HiberDemo.util.HibernateUtil;

public class AuthServices {
	
	public static void registerUser(String username, String password, String role) 
	{

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            session.save(user);

            tx.commit();
            System.out.println("Registration Successful.");

        } catch (Exception e) 
        {
            if (tx != null)
            	{
            		tx.rollback();
            	}
            System.out.println("Error: Username may already exist.");
            
        } finally 
        {
            if (session != null) 
            {
            		session.close();
            }
        }
    }

    public static void loginUser(String username, String password, Scanner sc) 
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<User> query = session.createQuery(
                "from User where username = :uname", User.class);
        query.setParameter("uname", username);

        User user = query.uniqueResult();

        session.close();

        if (user != null && user.getPassword().equals(password)) 
        {

            System.out.println("Login Successful.");
            System.out.println("Welcome " + user.getUsername());

            if (user.getRole().equalsIgnoreCase("ADMIN")) 
            {
                showAdminMenu(sc);
            } else 
            {
                showUserMenu(sc);
            }

        } 
        else 
        {
            System.out.println("Invalid Credentials."
            		+ "");
        }
    }


    public static void showAdminMenu(Scanner sc) 
    {

        while (true) 
        {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View all users");
            System.out.println("6. Logout");
            
            System.out.println("Enter your choice (1/2/3/4/5):");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) 
            {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter City: ");
                    String city = sc.nextLine();

                    StudentServices.addStudent(name, city);
                    break;

                case 2:
                		StudentServices.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New City: ");
                    city = sc.nextLine();

                    StudentServices.updateStudent(id, city);
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    id = sc.nextInt();

                    StudentServices.deleteStudent(id);
                    break;
                    
                case 5:

                		viewAllUsers();
                    break;

                case 6:
                    return;
            }
        }
    }


    public static void showUserMenu(Scanner sc) 
    {

        while (true) 
        {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. View Students");
            System.out.println("2. Logout");

            int choice = sc.nextInt();

            switch (choice) 
            {

                case 1:
                	StudentServices.viewStudents();
                    break;

                case 2:
                    return;
            }
        }
    }


    public static void viewAllUsers() 
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<User> query = session.createQuery("from User", User.class);

        for (User u : query.list()) 
        {
            System.out.println(u.getId() + " " + u.getUsername() + " " + u.getRole());
        }

        session.close();
    }

}
