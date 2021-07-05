package com.swap.dal;

import java.util.List;

import com.swap.bo.Notification;

public interface NotificationDAO extends DAO<Notification> {
	public Notification selectById(int id) throws DALException;

	public List<Notification> selectByRecipient(int recipientId) throws DALException;

	public List<Notification> selectBySender(int senderId) throws DALException;

	public List<Notification> selectByTypeAndRecipient(String type, int recipientId) throws DALException;

	public void delete(int id) throws DALException;

	public void deleteAllByRecipient(int recipientId) throws DALException;

	public List<Notification> selectByTypeAndSender(String type, int senderId) throws DALException;

	public void deleteByRecipientId(int userId) throws DALException;

	public void deleteBySenderId(int userId) throws DALException;

	public void updateIsRead(Notification notification) throws DALException;

}
