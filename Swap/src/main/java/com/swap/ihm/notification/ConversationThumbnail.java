package com.swap.ihm.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;

public class ConversationThumbnail implements Serializable {
	private UserManager userM = new UserManager();
	private int correspondantId, numberOfUnreadMessages = 0;
	private String correspondantName, lastMessageDate;
	private List<NotificationThumbnail> messages = new ArrayList<>();

	public ConversationThumbnail() {
	}

	public ConversationThumbnail(int correspondantId) throws BLLException {
		this.correspondantId = correspondantId;
		setCorrespondantName(correspondantId);
	}

	private void setCorrespondantName(int id) throws BLLException {
		correspondantName = userM.getById(id).getUsername();
	}

	public int getCorrespondantId() {
		return correspondantId;
	}

	public String getCorrespondantName() {
		return correspondantName;
	}

	public int getNumberOfUnreadMessages() {
		return numberOfUnreadMessages;
	}

	public String getLastMessageDate() {
		return lastMessageDate;
	}

	public List<NotificationThumbnail> getMessages() {
		return messages;
	}

	public void add(NotificationThumbnail message) {
		this.messages.add(message);
	}

	public void sortByDateDesc() {
		messages.sort((x, y) -> x.compareTo(y));
	}

	public void setLastMessageDate() {
		sortByDateDesc();
		this.lastMessageDate = messages.get(0).getDateAndTime();
	}

	public void setNumberOfUnread() {
		for (NotificationThumbnail message : messages)
			if (!message.isRead())
				this.numberOfUnreadMessages++;
	}

	public boolean hasUnread() {
		return numberOfUnreadMessages > 0;
	}
}
