package com.swap.ihm;

public enum AuctionStatus {
	CREATED("CREATED"), ONGOING("ONGOING"), OVER("OVER"), PICKED_UP("PICKED_UP");

	public final String status;

	private AuctionStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
