package com.example.fof_recommendation;

import java.io.Serializable;

public class LookingFor implements Serializable {

	String contact;
	String lookingFor;
	String location;

	public LookingFor(String contact, String lookingFor, String location) {
		this.contact = contact;
		this.lookingFor = lookingFor;
		this.location = location;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "contact :" + contact + "\nlookingfor: " + lookingFor + "\nlocation : " + location;
	}

}
