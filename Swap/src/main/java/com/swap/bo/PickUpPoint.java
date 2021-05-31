package com.swap.bo;

import java.io.Serializable;

public class PickUpPoint implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id, itemId;
	private String street, postcode, city;

	public PickUpPoint() {
	}

	public PickUpPoint(int itemId, String street, String postcode, String city) {
		this.itemId = itemId;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}

	public PickUpPoint(int id, int itemId, String street, String postcode, String city) {
		this(itemId, street, postcode, city);
		this.id = id;
	}

	public String toString() {
		String result = "Item number: " + this.itemId;
		result += "  Street: " + this.street + "\n";
		result += "Postcode: " + this.postcode + " City: " + this.city;
		return result;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
