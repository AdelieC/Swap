package com.swap.ihm.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.Notification;

public class ConversationThumbnail implements Serializable {
	UserManager userM = new UserManager();
	int correspondantId;
	String correspondantName;
	List<Notification> messages = new ArrayList<>();

	public ConversationThumbnail() {

	}

	public ConversationThumbnail(int correspondantId) throws BLLException {
		this.correspondantId = correspondantId;
		setCorrespondantName(correspondantId);
	}

	private void setCorrespondantName(int id) throws BLLException {
		correspondantName = userM.getById(id).getUsername();
	}

	public int getCorrespondant() {
		return correspondantId;
	}

	public void add(Notification message) {
		this.messages.add(message);
	}

	public void sortByDateDesc() {
		messages.sort((x, y) -> x.compareTo(y));
	}
}
