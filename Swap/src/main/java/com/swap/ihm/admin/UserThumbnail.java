package com.swap.ihm.admin;

public class UserThumbnail {

	private int userId;
	private String username;
	private String email;
	private boolean isAdmin;

	public UserThumbnail(int userId, String username, String email, boolean isAdmin) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

}
