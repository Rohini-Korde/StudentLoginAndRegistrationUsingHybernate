package com.HibernatePractice.HiberDemo;

import com.HibernatePractice.HiberDemo.service.AuthServices;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
        		System.out.println("\n--------------Menu-----------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Enter your choice (1/2/3):");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Username: ");
                    String uname = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String pass = sc.nextLine();

                    System.out.print("Enter Role (ADMIN/USER): ");
                    String role = sc.nextLine().toUpperCase();

                    AuthServices.registerUser(uname, pass, role);
                    break;

                case 2:
                    System.out.print("Enter Username: ");
                    uname = sc.nextLine();

                    System.out.print("Enter Password: ");
                    pass = sc.nextLine();

                    AuthServices.loginUser(uname, pass, sc);
                    break;

                case 3:
                		System.out.println("Bye byee.");
                    System.exit(0);
            }
        }
    }
    
}
