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
//		if (!client.establishConnection()) {
//			System.out.println("Could not establish connection. Try again later");
//			return;
//		}
        System.out.println("Registration :");
        Scanner sc = new Scanner(System.in);
//		client.sendString("", Codes.USER_DETAIL_CODE);
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
        client.sendString(userContact, Codes.USER_CONTACT_CODE);

        client.sendString("", Codes.END_OF_CONVO);

        try {
            User user = (User) client.getObject();
            System.out.println("IN REGISTER");
            System.out.println(user.toString());

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

            client.sendString(String.valueOf(user.getUserId()), Codes.USERID_CODE);
			client.sendString(password, Codes.USER_PASSWORD_CODE);
			client.sendString(Codes.END_OF_CONVO);

        } catch (IOException e) {

            System.out.println("Error receiving object");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            client.closeConnection();
        }

//		register.sendUserDetails(client, name, address, userContact);

//		try {
//			User user = (User) client.getObject();
//			System.out.println(user);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

    }

    public void sendUserDetails(Client client, String name, String address, String userContact) {

        Queue<String> data = new LinkedList<>();

        data.add(name);
        data.add(address);
        data.add(userContact);

        client.closeConnection();
    }

}
