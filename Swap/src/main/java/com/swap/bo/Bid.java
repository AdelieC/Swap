package com.swap.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id, userId, auctionId, bidPrice;
	private LocalDate date;
	// private User user;

	public Bid() {
	}

	public Bid(int userId, int auctionId, int bidPrice, LocalDate date) {
		this.userId = userId;
		this.auctionId = auctionId;
		this.bidPrice = bidPrice;
		this.date = date;
	}

	public Bid(int id, int userId, int auctionId, int bidPrice, LocalDate date) {
		this(userId, auctionId, bidPrice, date);
		this.setId(id);
	}

	public String toString() {
		String result = "";
		result += "User ID: " + this.userId;
		result += " Auction ID: " + this.userId + "\n";
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

	public int getAuctionId() {
		return this.auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
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
