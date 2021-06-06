package com.swap.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username, lastName, firstName, email, telephone, street, postcode, city, password;
	private int userId, balance;
	private boolean isAdmin;
	private List<Auction> auctionList = new ArrayList<>();

	public User() {
	}

	public User(String username, String lastName, String firstName, String email, String telephone, String street,
			String postcode, String city, String password, int balance, boolean isAdmin) {
		try {
			this.setUsername(username);
			this.setLastName(lastName);
			this.setFirstName(firstName);
			this.setEmail(email);
			this.setTelephone(telephone);
			this.setStreet(street);
			this.setPostcode(postcode);
			this.setCity(city);
			this.setPassword(password);
			this.setBalance(balance);
			this.setIsAdmin(isAdmin);
		} catch (BOException e) {
			e.printStackTrace();
		}
	}

	public User(int userId, String username, String lastName, String firstName, String email, String telephone,
			String street, String postcode, String city, String password, int balance, boolean isAdmin) {
		this(username, lastName, firstName, email, telephone, street, postcode, city, password, balance, isAdmin);
		this.setUserId(userId);
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

	public String getTelephone() {
		return telephone;
	}

	public int getBalance() {
		return balance;
	}

	public List<Auction> getAuctionList() {
		return auctionList;
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

	public void setPassword(String password) throws BOException {
		try {
			this.password = BOCleaner.cleanPassword(password);
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

	public void setAuctionList(List<Auction> auctionList) throws BOException {
		if (auctionList.isEmpty())
			throw new BOException("Cannot set auction list with empty list for user " + this.username);
		this.auctionList = auctionList;
	}

	public void addAuction(Auction auction) {
		this.auctionList.add(auction);
	}

	public void removeAuction(int index) throws BOException {
		if (index < 0 || index >= auctionList.size())
			throw new BOException(
					"List of auctions for user " + this.username + " does not have entry number " + index);
		this.auctionList.remove(index);
	}

	@Override
	public String toString() {
		return "User n°" + userId + " : " + username + " (" + lastName + ", " + firstName + ") " + "INFOS -> " + email
				+ " | " + telephone + " | " + street + " | " + postcode + " | " + city + " | " + balance
				+ " points | admin = " + isAdmin + " | pw = " + password;
	}

}
