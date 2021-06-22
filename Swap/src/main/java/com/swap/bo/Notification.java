package com.swap.bo;

import java.io.Serializable;

public class Notification implements Serializable {
	// TODO : handle verifications in setters
	private static final long serialVersionUID = 1L;
	private int id, recipientId, senderId, auctionId;
	private String type;
	private String content;
	private boolean isRead;
	private java.sql.Timestamp timestamp;

	enum Type {
		BID,
		MESSAGE,
		AUCTION,
		NOTIFICATION;

		public static boolean check(String type) {
			boolean isType = false;
			int i = 0;
			do {
				isType = Type.values()[i].name().equals(type);
				i++;
			} while (!isType && i < Type.values().length);
			return isType;
		}
	}

	public Notification() {

	}

	public Notification(int recipientId, int senderId, String type, String content) throws BOException {
		setRecipientId(recipientId);
		setSenderId(senderId);
		setType(type);
		this.isRead = false;
		this.auctionId = 0;
	}

	public Notification(int recipientId, int senderId, String type, String content, int auctionId) throws BOException {
		setRecipientId(recipientId);
		setSenderId(senderId);
		setType(type);
		setContent(content);
		setAuctionId(auctionId);
		this.isRead = false;
	}

	public Notification(int id, int recipientId, int senderId, String type, String content, boolean isRead,
			int auctionId, java.sql.Timestamp timestamp) {
		this.id = id;
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.type = type;
		this.content = content;
		this.isRead = isRead;
		this.auctionId = auctionId;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public int getSenderId() {
		return senderId;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public boolean isRead() {
		return isRead;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public void setType(String type) throws BOException {
		if (!Type.check(type))
			throw new BOException("Invalid notification type");
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRead(boolean read) {
		this.isRead = read;
	}

	private void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

}
