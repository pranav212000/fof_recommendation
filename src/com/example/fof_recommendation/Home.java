package com.example.fof_recommendation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Home {
	String contact;
	Client client;
	static boolean isLoggedIn;

	Home() {

	}

	Home(String contact, Client client) {
		this.contact = contact;
		this.client = client;
	}

	public void readOption() {
		System.out.println("1. Post");
		System.out.println("2. Feed");
		String input = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			input = br.readLine();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		if (input.equals("1")) {
			makePost();
		} else if (input.equals("2")) {
			showFeed();
		}
	}

	public void getOption() {

		
			System.out.println("1. Post");
			System.out.println("2. Feed");
			String input = "";
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				input = br.readLine();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			if (input.equals("1")) {
				makePost();
			} else if (input.equals("2")) {
				showFeed();
			}

		
	}

	public void makePost() {
		try {
			System.out.println("Choose type of post : ");
			System.out.println("1. Looking for something");
			System.out.println("2. Offering something");
			System.out.println("3. Recommending something");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String option = "";
			String question = "";
			option = br.readLine();
			String what = "";
			String location = "";
			switch (option) {
			case "1":
				question = "What are you looking for ?";
				System.out.println(question);

				while (what.isEmpty()) {
					what = br.readLine();
					if (what.isEmpty())
						System.out.println("Enter what you are looking for");
				}
				System.out.println("Enter the location : ");
				while (location.isEmpty()) {
					location = br.readLine();
					if (what.isEmpty())
						System.out.println("Enter location");
				}

				client.sendString(Codes.POST_LOOKING_FOR);
				client.sendString(contact, Codes.USER_CONTACT_CODE);
				client.sendString(what, Codes.LOOKING_FOR);
				client.sendString(location, Codes.LOCATION);
				client.sendString(Codes.END_OF_CONVO);
				LookingFor lookingFor;
				try {
					lookingFor = (LookingFor) client.getObject();
					if (lookingFor != null) {
						System.out.println("POST SUCCESSFUL");
						System.out.println(lookingFor.toString());
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				break;

			}
		} catch (Exception e) {
			System.out.println(e.toString());

		}
//		scanner.close();
	}

	public void showFeed() {

	}

}
