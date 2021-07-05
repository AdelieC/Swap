package com.swap.ihm.notification;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.Notification;

public class NotificationThumbnail implements Serializable, Comparable<NotificationThumbnail> {
	private static final long serialVersionUID = 1L;
	AuctionManager auctionM = new AuctionManager();
	UserManager userM = new UserManager();
	private int id, auctionId;
	private String type, senderName, recipientName, auctionName;
	private String content;
	private boolean isRead;
	private String dateAndTime;
	private Timestamp timestamp;

	public NotificationThumbnail(int id, int recipientId, int senderId, String type, String content, boolean isRead,
			int auctionId, java.sql.Timestamp timestamp) throws BLLException {
		this.id = id;
		setRecipientName(recipientId);
		setSenderName(senderId);
		this.type = type;
		this.content = content;
		this.isRead = isRead;
		setAuctionName(auctionId);
		this.auctionId = auctionId;
		setDateAndTime(timestamp);
		this.timestamp = timestamp;
	}

	public NotificationThumbnail(Notification notification) throws BLLException {
		this.id = notification.getId();
		setRecipientName(notification.getRecipientId());
		setSenderName(notification.getSenderId());
		this.type = notification.getType();
		this.content = notification.getContent();
		this.isRead = notification.isRead();
		this.auctionId = notification.getAuctionId();
		setAuctionName(notification.getAuctionId());
		setDateAndTime(notification.getTimestamp());
		this.timestamp = notification.getTimestamp();
	}

	public int getId() {
		return id;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public String getType() {
		return type;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public String getContent() {
		return content;
	}

	public boolean isRead() {
		return isRead;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	private void setDateAndTime(Timestamp timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(Locale.FRENCH);
		ZonedDateTime dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault());
		dateAndTime = dateTime.format(formatter);
	}

	private void setAuctionName(int auctionId) throws BLLException {
		if (auctionId > 0)
			auctionName = auctionM.getById(auctionId).getName();
	}

	private void setSenderName(int senderId) throws BLLException {
		senderName = userM.getById(senderId).getUsername();

	}

	private void setRecipientName(int recipientId) throws BLLException {
		recipientName = userM.getById(recipientId).getUsername();
	}

	@Override
	public int compareTo(NotificationThumbnail o) {
		return this.timestamp.compareTo(o.getTimestamp());
	}

}
