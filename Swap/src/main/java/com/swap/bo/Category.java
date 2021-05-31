package com.swap.bo;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private int categoryId;
	private String label;

	public Category() {
	}

	public Category(int categoryId, String label) {
		this.categoryId = categoryId;
		this.label = label;
	}

	public String toString() {
		String result = "Category number: " + this.categoryId;
		result += " Label: " + this.label;
		return result;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
