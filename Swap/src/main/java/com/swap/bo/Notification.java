package com.swap.bo;

import java.io.Serializable;

import com.swap.ihm.notification.NotificationType;

public class Notification implements Serializable, Comparable<Notification> {
	// TODO : handle verifications in setters to prevent botnets and DoS attacks
	private static final long serialVersionUID = 1L;
	private int id, recipientId, senderId, auctionId;
	private String type;
	private String content;
	private boolean isRead;
	private java.sql.Timestamp timestamp;

	public Notification() {

	}

	public Notification(int recipientId, int senderId, NotificationType type, String content) throws BOException {
		setRecipientId(recipientId);
		setSenderId(senderId);
		setType(type);
		setContent(content);
		this.auctionId = 0;
		this.isRead = false;
	}

	public Notification(int recipientId, int senderId, NotificationType type, String content, int auctionId)
			throws BOException {
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

	public void setType(NotificationType type) throws BOException {
		this.type = type.name();
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

	@Override
	public int compareTo(Notification o) {
		return this.timestamp.compareTo(o.getTimestamp());
	}

}
