package com.swap.bo;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BALANCE = 50;
	private String username, lastName, firstName, email, telephone, street, postcode, city, password, salt;
	private int userId, balance;
	private boolean isAdmin, wasDisabled;

	public User() {
	}

	public User(String username) throws BOException {
		this.setUsername(username);
	}

	public User(String username, String lastName, String firstName, String email, String telephone, String street,
			String postcode, String city, String password) throws BOException {
		this.setUsername(username);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setStreet(street);
		this.setPostcode(postcode);
		this.setCity(city);
		this.setSalt();
		this.setPassword(password, this.salt);
		this.balance = DEFAULT_BALANCE;
		this.isAdmin = false;
		this.wasDisabled = false;
	}

	public User(int userId, String username, String lastName, String firstName, String email, String telephone,
			String street, String postcode, String city) throws BOException {
		this.setUserId(userId);
		this.setUsername(username);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setStreet(street);
		this.setPostcode(postcode);
		this.setCity(city);
	}

	public User(int userId, String username, String lastName, String firstName, String email, String telephone,
			String street, String postcode, String city, String password, String salt, int balance, boolean isAdmin,
			boolean wasDisabled) {
		this.userId = userId;
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.telephone = telephone;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
		this.balance = balance;
		this.isAdmin = isAdmin;
		this.wasDisabled = wasDisabled;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public String getStreet() {
		return street;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCity() {
		return city;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public String getTelephone() {
		return telephone;
	}

	public int getBalance() {
		return balance;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String username) throws BOException {
		try {
			this.username = BOCleaner.cleanASCII(username, 30);
		} catch (BOException e) {
			throw new BOException("Couldn't set username", e);
		}
	}

	public void setLastName(String lastName) throws BOException {
		try {
			this.lastName = BOCleaner.cleanString(lastName, 30);
		} catch (BOException e) {
			throw new BOException("Couldn't set last name", e);
		}
	}

	public void setFirstName(String firstName) throws BOException {
		try {
			this.firstName = BOCleaner.cleanString(firstName, 30);
		} catch (BOException e) {
			throw new BOException("Couldn't set first name", e);
		}
	}

	public void setEmail(String email) throws BOException {
		try {
			this.email = BOCleaner.cleanEmail(email);
		} catch (BOException e) {
			throw new BOException("Couldn't set email", e);
		}
	}

	public void setStreet(String street) throws BOException {
		try {
			this.street = BOCleaner.cleanASCII(street, 30);
		} catch (BOException e) {
			throw new BOException("Couldn't set street", e);
		}
	}

	public void setPostcode(String postcode) throws BOException {
		try {
			this.postcode = BOCleaner.cleanString(postcode, 10);
		} catch (BOException e) {
			throw new BOException("Couldn't set postcode", e);
		}
	}

	public void setCity(String city) throws BOException {
		try {
			this.city = BOCleaner.cleanString(city, 30);
		} catch (BOException e) {
			throw new BOException("Couldn't set city", e);
		}
	}

	private void setSalt() {
		this.salt = PasswordUtils.generateSalt();
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPassword(String password, String salt) throws BOException {
		try {
			this.password = PasswordUtils.getSecuredPassword(BOCleaner.cleanPassword(password), salt);
		} catch (BOException e) {
			throw new BOException("Couldn't set password", e);
		}
	}

	public void setTelephone(String telephone) throws BOException {
		try {
			this.telephone = BOCleaner.cleanTelephone(telephone);
		} catch (BOException e) {
			throw new BOException("Couldn't set telephone", e);
		}
	}

	public void setBalance(int balance) throws BOException {
		try {
			this.balance = BOCleaner.cleanAmount(balance, 500000);
		} catch (BOException e) {
			throw new BOException("Couldn't set balance", e);
		}
	}

	public void setIsAdmin(boolean isAdmin) throws BOException {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User n°" + userId + " : " + username;
	}

	public boolean login(String passwordToValidate) throws BOException {
		return PasswordUtils.isPasswordCorrect(passwordToValidate, password, salt);
	}

	public boolean wasDisabled() {
		return wasDisabled;
	}

	public void setWasDisabled(boolean disable) {
		wasDisabled = disable;
	}

}
