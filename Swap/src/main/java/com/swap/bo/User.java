package com.swap.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//TODO : add constraints in setters to transform what should get transformed (trimed, checked with business rules, parsed) before getting to the BLL
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username, lastName, firstName, email, telephone, street, postcode, city, password;
	private int userId, credit;
	private boolean isAdmin;
	private List<Auction> auctionList = new ArrayList<>();

	public User() {
	}

	public User(String username, String lastName, String firstName, String email, String telephone, String street,
			String postcode, String city, String password, int credit, boolean isAdmin) {
		this.setUsername(username);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setStreet(street);
		this.setPostcode(postcode);
		this.setCity(city);
		this.setPassword(password);
		this.setCredit(credit);
		this.setIsAdmin(isAdmin);
	}

	public User(int userId, String username, String lastName, String firstName, String email, String telephone,
			String street, String postcode, String city, String password, int credit, boolean isAdmin) {
		this(username, lastName, firstName, email, telephone, street, postcode, city, password, credit, isAdmin);
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

	public int getCredit() {
		return credit;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setAuctionList(List<Auction> auctionList) {
		this.auctionList = auctionList;
	}

	public String toString() {
		return "User n°" + userId + " : " + username + " (" + lastName + ", " + firstName + ") " + "INFOS -> " + email
				+ " | " + telephone + " | " + street + " | " + postcode + " | " + city + " | " + credit
				+ " points | admin = " + isAdmin + " | pw = " + password;
	}

	public void addAuction(Auction auction) {
		this.auctionList.add(auction);
	}

}
