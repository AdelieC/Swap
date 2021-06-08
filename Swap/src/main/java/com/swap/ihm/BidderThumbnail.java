package com.swap.ihm;

import java.io.Serializable;

public class BidderThumbnail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private int bid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

}
