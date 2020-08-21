package com.example.fof_recommendation;

public class Contact {
	String userId;
	String phoneNo;



	public Contact(String userId, String phoneNo) {
		this.userId = userId;
		this.phoneNo = phoneNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
