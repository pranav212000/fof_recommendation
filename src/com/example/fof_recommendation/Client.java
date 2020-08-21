package com.example.fof_recommendation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {
    String address;
    int port;
    private Socket socket = null;
//    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
//    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;


    public Client(String address, int port) {
        this.address = address;
        this.port = port;
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
//
//            inputStream = new DataInputStream(System.in);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("HERE 2");
            outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("HERE 1");

            

            System.out.println("Connection Established!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public boolean establishConnection() {
//
//    }


    public void closeConnection() {
//        close the connection
        try {
//            inputStream.close();
            outputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void sendObject(Object object) {
//        try {
//            objectOutputStream.writeObject(object);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void sendString(String string) {
        try {
            outputStream.writeUTF(string);
        } catch (IOException e) {
            System.out.println("Error sending message");
            e.printStackTrace();
        }
    }


    public void sendString(String string, String code) {
        try {

//            System.out.println("Writing string : " + string);
            outputStream.writeUTF(code);
            outputStream.writeUTF(string);
        } catch (IOException e) {
            System.out.println("Error sending message");
            e.printStackTrace();
        }
    }

    public void sendInt(int i) {
        try {
            outputStream.writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getObject() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }


}
