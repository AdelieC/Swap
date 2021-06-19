package com.swap.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id, initialPrice, salePrice, userId, categoryId;
	private String name, description, status;
	private LocalDate startDate, endDate;
	private List<Picture> pictures = new ArrayList<>();

	public Auction() {
	}

	public Auction(String name, String description, LocalDate startDate, LocalDate endDate, int categoryId,
			int initialPrice, int userId) {
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.initialPrice = initialPrice;
		this.salePrice = initialPrice;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = "CREATED";
	}

	public Auction(int id, String name, String description, LocalDate startDate, LocalDate endDate, int categoryId,
			int initialPrice, int salePrice, int userId) {
		this(name, description, startDate, endDate, categoryId, initialPrice, userId);
		this.id = id;
		this.salePrice = salePrice;
	}

	public Auction(int id, String name, String description, LocalDate startDate, LocalDate endDate, int categoryId,
			int initialPrice, int salePrice, int userId, String status) {
		this(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice, userId);
		this.status = status;
	}

	@Override
	public String toString() {
		String result = "AUCTION:\n";
		result += "Name: " + this.name + "\n";
		result += "Description: " + this.description + "\n";
		result += "Category: " + this.categoryId + " Price: " + this.salePrice + "\n";
		result += "Seller ID: " + this.userId + "\n";
		result += "Start date: " + this.startDate + " End date: " + this.endDate + "\n";
		return result;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInitialPrice() {
		return this.initialPrice;
	}

	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	public int getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures.retainAll(pictures);
	}
}
