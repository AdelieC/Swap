package com.swap.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id, userId, itemId, bidPrice;
	private LocalDate date;

	public Bid() {
	}

	public Bid(int userId, int itemId, int bidPrice, LocalDate date) {
		this.userId = userId;
		this.itemId = itemId;
		this.bidPrice = bidPrice;
		this.date = date;
	}

	public Bid(int id, int userId, int itemId, int bidPrice, LocalDate date) {
		this(userId, itemId, bidPrice, date);
		this.setId(id);
	}

	public String toString() {
		String result = "";
		result += "User number: " + this.userId;
		result += " Item number: " + this.userId + "\n";
		result += "Sale price: " + this.bidPrice;
		result += " Date: " + this.date + "\n";
		return result;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getBidPrice() {
		return this.bidPrice;
	}

	public void setBidPrice(int sale_price) {
		this.bidPrice = sale_price;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
