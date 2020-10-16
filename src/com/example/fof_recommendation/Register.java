package com.example.fof_recommendation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Register {

	public static void main(String[] args) {
		Register register = new Register();
//		System.out.println(Codes.USER_DETAIL_CODE);
		Client client = new Client("localhost", 5000);
		boolean stop = false;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Select :");
			System.out.println("1. Signup");
			System.out.println("2. Login");

			String option = "";
			option = scanner.nextLine();
			if (option.equals("1"))
				stop = register.signup(client);
			else if (option.equals("2"))
				stop = register.login(client);

			if (stop)
				break;
		}
		scanner.close();
	}

	public boolean signup(Client client) {

		System.out.println("Registration :");
		Scanner sc = new Scanner(System.in);
		String line = "";

		client.sendString(Codes.USER_DETAIL_CODE);

		System.out.println("Enter your Name :");
		String name = sc.nextLine();
		client.sendString(name, Codes.USER_NAME_CODE);

		System.out.println("Enter your last name :");
		String lastname = sc.nextLine();
		client.sendString(lastname, Codes.USER_LAST_NAME);

		System.out.println("Enter address :");
		String address = sc.nextLine();
		client.sendString(address, Codes.USER_ADDR_CODE);

		System.out.println("Enter your contact");
		String userContact = sc.nextLine();

		while (userContact.length() != 10) {
			System.out.println("Enter valid contact");
			userContact = sc.nextLine();
		}

		client.sendString(userContact, Codes.USER_CONTACT_CODE);
//        client.sendString("", Codes.END_OF_CONVO);

		try {
//            System.out.println("IN REGISTER");
//            System.out.println(user.toString());

			String password = "", checkpwd = " ";

			do {
				System.out.println("Enter password :");
				password = sc.nextLine();
				System.out.println("Enter the password again :");
				checkpwd = sc.nextLine();
				if (!password.equals(checkpwd)) {
					System.out.println("Passwords don't match, please enter again");
				}

			} while (!password.equals(checkpwd));

			client.sendString(password, Codes.USER_PASSWORD_CODE);
			client.sendString(Codes.END_OF_CONVO);

			User user = (User) client.getObject();
			System.out.println("IN REGISTER :");
			System.out.println(user.toString());
			return true;

		} catch (IOException e) {

			System.out.println("Error receiving object");
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			sc.close();
			client.closeConnection();
		}
	}

	public boolean login(Client client) {
		System.out.println("Enter your contact number :");
		String contact = "";
		Scanner scanner = new Scanner(System.in);
		contact = scanner.nextLine();

		System.out.println("Enter password :");
		String password = scanner.nextLine();

		client.sendString(Codes.USER_LOGIN_CODE);
		client.sendString(contact, Codes.USER_CONTACT_CODE);
		client.sendString(password, Codes.USER_PASSWORD_CODE);
		client.sendString(Codes.END_OF_CONVO);

		try {
			User user = (User) client.getObject();

			System.out.println("Login Successful");
			Home home = new Home(user.getUserContact(), client);
			home.getOption();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		scanner.close();
		return true;
	}

}
