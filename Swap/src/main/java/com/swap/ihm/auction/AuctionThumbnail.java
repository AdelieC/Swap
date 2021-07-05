package com.swap.ihm.auction;

import java.io.Serializable;
import java.time.LocalDate;

import com.swap.bll.BLLException;
import com.swap.bo.Picture;

public class AuctionThumbnail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String item, seller;
	private int price, id;
	private LocalDate startDate, endDate;
	private String pictureName;

	public AuctionThumbnail() {
	}

	public AuctionThumbnail(int id, String item, int price, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.item = item;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public AuctionThumbnail(int id, String item, int price, LocalDate startDate, LocalDate endDate, Picture picture) {
		this(id, item, price, startDate, endDate);
		this.pictureName = picture.getName() + "." + picture.getExtension();
	}

	public AuctionThumbnail(int id, String item, int price, LocalDate startDate, LocalDate endDate, String seller)
			throws BLLException {
		this(id, item, price, startDate, endDate);
		this.seller = seller;
	}

	public AuctionThumbnail(int id, String item, int price, LocalDate startDate, LocalDate endDate, String seller,
			Picture picture) throws BLLException {
		this(id, item, price, startDate, endDate, seller);
		this.pictureName = picture.getName() + "." + picture.getExtension();
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate date) {
		this.startDate = date;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate date) {
		this.endDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPictureName() {
		return pictureName;
	}

}
