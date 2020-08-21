package com.example.fof_recommendation;

import java.util.HashSet;
import java.util.Set;

public class Contacts {
	HashSet<Contact> contacts = new HashSet<>();

	public HashSet<Contact> getContacts() {
		return contacts;
	}

	public void addContact(Contact contact) {
		contacts.add(contact);
	}
	
	public HashSet<Contact> getCommonContacts(Contacts contacts) {
		HashSet<Contact> intersection = new HashSet<>(this.contacts);
		intersection.retainAll(contacts.contacts);
		
		System.out.println(intersection);
		return intersection;
	}
	
	public void removeContact(Contact contact) {
		this.contacts.remove(contact);
	}

}
