package com.example.fof_recommendation;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String lastname;
    String userId;
    Contacts contacts = null;
    String address;
    String userContact;

	public User(String name, String lastname, String userId) {
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

    public User(String userId, String name, String lastname, String address, String userContact) {
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
