package com.example.fof_recommendation;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

	private Socket socket;
	private ServerSocket serverSocket;
	private DataInputStream inputStream;
	private ObjectOutputStream objectOutputStream;
	private JDBC jdbc;

	public Server(int port) {
		String initCode;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = serverSocket.accept();

			System.out.println("Client Accepted");
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			try {
				while (true) {
					initCode = inputStream.readUTF();
					System.out.println(initCode);

					switch (initCode) {
					case Codes.USER_DETAIL_CODE:
						registerUser();
						break;

					case Codes.USER_LOGIN_CODE:
						loginUser();
						break;

					case Codes.POST_LOOKING_FOR:
						postLookingFor();
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				System.out.println("Closing connection");

				// close connection
				objectOutputStream.close();
				inputStream.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server(5000);
	}

	public void postLookingFor() {
		try {

			boolean isReading = true;
			String name = "", address = "", userContact = "", lastname = "", password = "";
			String contact = "";
			String lookingFor = "";
			String location = "";
			while (isReading) {
				String code = inputStream.readUTF();
				switch (code) {
				case Codes.USER_CONTACT_CODE:
					contact = inputStream.readUTF();
					break;

				case Codes.LOOKING_FOR:
					lookingFor = inputStream.readUTF();
					break;

				case Codes.LOCATION:
					location = inputStream.readUTF();
					break;

				case Codes.END_OF_CONVO:
					isReading = false;
					break;

				default:
					System.out.println("Reading done!");
					isReading = false;

				}
			}

			jdbc = new JDBC();

//                TODO change db for prod.
			try {
				jdbc.useDatabase("sdldev");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			boolean result = jdbc.postLookingFor(contact, lookingFor, location);
			
			if(result) {
				sendObject(new LookingFor(contact, lookingFor, location));
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jdbc.closeConenction();
		}

	}

	public void registerUser() {
		try {

			boolean isReading = true;
			String name = "", address = "", userContact = "", lastname = "", password = "";
			while (isReading) {
				String code = inputStream.readUTF();
				switch (code) {
				case Codes.USER_NAME_CODE:
					name = inputStream.readUTF();
					break;

				case Codes.USER_LAST_NAME:
					lastname = inputStream.readUTF();
					break;

				case Codes.USER_ADDR_CODE:
					address = inputStream.readUTF();
					break;
				case Codes.USER_CONTACT_CODE:
					userContact = inputStream.readUTF();
					break;

				case Codes.USER_PASSWORD_CODE:
					password = inputStream.readUTF();
					break;

				case Codes.END_OF_CONVO:
					isReading = false;
					break;

				default:
					System.out.println("Reading done!");
					isReading = false;

				}
			}

			jdbc = new JDBC();

//                TODO change db for prod.
			try {
				jdbc.useDatabase("sdldev");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			User user = jdbc.addUser(name, lastname, address, userContact, password);
			System.out.println("IN SERVER");
			System.out.println(user.toString());
			sendObject(user);

//                while (!inputStream.readUTF().equals(Codes.END_OF_CONVO)) {
//                    if (inputStream.readUTF().equals(Codes.USERID_CODE)) {
//                        userid = Integer.parseInt(inputStream.readUTF());
//                    }
//                    if (inputStream.readUTF().equals(Codes.USER_PASSWORD_CODE)) {
//                        password = inputStream.readUTF();
//                    }
//                }

//                jdbc.addPassword(userid, password);

			System.out.println(name);
			System.out.println(lastname);
			System.out.println(address);
			System.out.println(userContact);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jdbc.closeConenction();
		}

	}

	public void loginUser() {
		try {
			boolean isReading = true;
			String userContact = "";
			String password = "";
			JDBC jdbc = new JDBC();
			try {
				jdbc.useDatabase("sdldev");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			User user = null;
			while (isReading) {
				String code = inputStream.readUTF();
				switch (code) {

				case Codes.USER_CONTACT_CODE:

					userContact = inputStream.readUTF();
					break;

				case Codes.USER_PASSWORD_CODE:
					password = inputStream.readUTF();
					if (!userContact.isEmpty()) {
						user = jdbc.checkUser(userContact, password);
						if (user != null) {
							sendObject(user);
						}
					}
					break;

				case Codes.END_OF_CONVO:
					isReading = false;
					break;

				default:
					System.out.println("Reading done!");
					isReading = false;

				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void sendObject(Object object) {
		try {
			objectOutputStream.writeObject(object);
		} catch (IOException e) {
			System.out.println("Error writing object");
			e.printStackTrace();
		}
	}
	
	public void sendBoolean(boolean b) {
		try {
			objectOutputStream.writeBoolean(b);
		} catch (IOException e) {
			System.out.println("Error writing boolean");
			e.printStackTrace();
		}
	}

}
