package com.example.fof_recommendation;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String lastname;
    private int userId;
    private Contacts contacts = null;
    private String address;
    private String userContact;

	public User(String name, String lastname, int userId) {
		this.name = name;
		this.lastname = lastname;
		this.userId = userId;
	}

    public User(String name, String lastname, String address, String userContact) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.userContact = userContact;
    }

    public User(int userId, String name, String lastname, String address, String userContact) {
        this.name = name;
        this.lastname = lastname;
        this.userId = userId;
        this.address = address;
        this.userContact = userContact;
    }

    public void addContact(Contact contact) {
        if (this.contacts == null) {
            this.contacts = new Contacts();
        }
        this.contacts.addContact(contact);
    }

    public void addPersonalContact(String contact) {
		this.userContact = contact;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", contacts=" + contacts +
                ", address='" + address + '\'' +
                ", userContact=" + userContact +
                '}';
    }
}
