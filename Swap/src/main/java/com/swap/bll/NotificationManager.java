package com.swap.bll;

import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Notification;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.NotificationDAO;

public class NotificationManager {
	private NotificationDAO NotificationDAO;

	public NotificationManager() {
		this.NotificationDAO = DAOFactory.getNotificationDAO();
	}

	public void create(Notification n) throws BLLException {
		if (!isValid(n))
			throw new BLLException("Notification is not valid");
		try {
			NotificationDAO.create(n);
		} catch (DALException e) {
			throw new BLLException("Failed to create notification", e);
		}
	}

	public List<Notification> getAll() throws BLLException {
		List<Notification> list = new ArrayList<Notification>();
		try {
			list = NotificationDAO.read();
		} catch (DALException e) {
			throw new BLLException("Failed to get all notifications", e);
		}
		return list;
	}

	public void update(Notification n) throws BLLException {
		if (isValid(n)) {
			try {
				this.NotificationDAO.update(n);
			} catch (DALException e) {
				throw new BLLException("Failed to update notification with id = " + n.getId(), e);
			}
		}
	}

	public void delete(int id) throws BLLException {
		try {
			this.NotificationDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("Failed to delete notification with id = " + id, e);
		}
	}

	public void delete(Notification n) throws BLLException {
		this.delete(n.getId());
	}

	public Notification getById(int id) throws BLLException {
		Notification notification = null;
		try {
			notification = NotificationDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch notification with id = " + id, e);
		}
		return notification;
	}

	public List<Notification> getByRecipient(int recipientId) throws BLLException {
		List<Notification> notifications = new ArrayList<>();
		try {
			notifications = NotificationDAO.selectByRecipient(recipientId);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch notifications for user number " + recipientId, e);
		}
		return notifications;
	}

	public List<Notification> getByTypeAndRecipient(String type, int recipientId) throws BLLException {
		List<Notification> notifications = new ArrayList<>();
		try {
			notifications = NotificationDAO.selectByTypeAndRecipient(type, recipientId);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch notifications of type " + type + " for user number " + recipientId,
					e);
		}
		return notifications;
	}

	public boolean isValid(Notification n) {
		return true;
		// TODO : complete with BLLValidator
	}

	public List<Notification> getBySender(int senderId) throws BLLException {
		List<Notification> notifications = new ArrayList<>();
		try {
			notifications = NotificationDAO.selectBySender(senderId);
		} catch (DALException e) {
			throw new BLLException("Failed to fetch notifications sent by user number " + senderId, e);
		}
		return notifications;
	}
}
