package com.swap.ihm;

import java.io.Serializable;
import java.time.LocalDate;

public class AuctionThumbnail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String item, seller;
	private int price, id;
	private LocalDate date;

	public AuctionThumbnail() {
	}

	public AuctionThumbnail(int id, String item, int price, LocalDate date, String seller) {
		this.id = id;
		this.item = item;
		this.price = price;
		this.date = date;
		this.seller = seller;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
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
