package com.swap.bo;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String label;

	public Category() {
	}

	public Category(String label) {
		this.label = label;
	}

	public Category(int categoryId, String label) {
		this(label);
		this.id = categoryId;
	}

	public String toString() {
		String result = "Category number: " + this.id;
		result += " Label: " + this.label;
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int categoryId) {
		this.id = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
