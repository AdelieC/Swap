package com.swap.ihm.notification;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.UserManager;

public class NotificationThumbnail {
	AuctionManager auctionM = new AuctionManager();
	UserManager userM = new UserManager();
	private int auctionId;
	private String type, senderName, recipientName, auctionName;
	private String content;
	private boolean isRead;
	private String dateAndTime;

	public NotificationThumbnail(int recipientId, int senderId, String type, String content, boolean isRead,
			int auctionId, java.sql.Timestamp timestamp) throws BLLException {
		setRecipientName(recipientId);
		setSenderName(senderId);
		this.type = type;
		this.content = content;
		this.isRead = isRead;
		setAuctionName(auctionId);
		setDateAndTime(timestamp);
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

	private void setDateAndTime(Timestamp timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(Locale.FRENCH);
		ZonedDateTime dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault());
		dateAndTime = dateTime.format(formatter);
	}

	private void setAuctionName(int auctionId) throws BLLException {
		auctionName = auctionM.getById(auctionId).getName();
	}

	private void setSenderName(int senderId) throws BLLException {
		senderName = userM.getById(senderId).getUsername();

	}

	private void setRecipientName(int recipientId) throws BLLException {
		recipientName = userM.getById(recipientId).getUsername();
	}

}
