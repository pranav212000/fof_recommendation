package com.example.fof_recommendation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Home {

	User user;
	Client client;
	static boolean isLoggedIn;

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

	public static void main(String[] args) {

		Home home = new Home();
		while (true) {
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
				home.makePost();
			} else if (input.equals("2")) {
				home.showFeed();
			}
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
				what = br.readLine();
				System.out.println("Enter the location : ");
				location = br.readLine();

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
