package com.swap.ihm.notification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.swap.bll.BLLException;
import com.swap.bll.NotificationManager;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/account/messages" })
public class ViewNotificationsServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_MSG_JSP = "/WEB-INF/ViewMessages.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setAllNotificationsThumbnails(request);
			sendToJSP(VIEW_MSG_JSP, request, response);
		} catch (BLLException e) {
			// TODO : error 500
			e.printStackTrace();
		}
	}

	private void setAllNotificationsThumbnails(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		NotificationManager notificationM = new NotificationManager();
		int userId = ((User) session.getAttribute("user")).getUserId();
		setConversations(notificationM, userId, request);
		for (NotificationType type : NotificationType.values()) {
			if (!type.equals(NotificationType.MESSAGE))
				setNotificationsThumbnails(type, notificationM, userId, request);
		}
	}

	private void setConversations(NotificationManager notificationM, int userId, HttpServletRequest request)
			throws BLLException {
		List<Notification> messagesSent = notificationM.getByTypeAndSender(NotificationType.MESSAGE.name(), userId);
		List<Notification> messagesReceived = notificationM.getByTypeAndRecipient(NotificationType.MESSAGE.name(),
				userId);
		List<ConversationThumbnail> conversations = createConversations(messagesReceived, messagesSent);
		request.setAttribute("conversations", conversations);
	}

	private List<ConversationThumbnail> createConversations(List<Notification> messagesReceived,
			List<Notification> messagesSent) throws BLLException {
		List<ConversationThumbnail> conversations = new ArrayList<>();
		List<Integer> allCorrespondants = new ArrayList<>();
		List<Integer> correspondants = new ArrayList<>();
		messagesReceived.forEach(message -> allCorrespondants.add(message.getSenderId()));
		messagesSent.forEach(message -> allCorrespondants.add(message.getRecipientId()));
		correspondants = allCorrespondants.stream().distinct().collect(Collectors.toList());
		for (int correspondant : correspondants) {
			conversations.add(new ConversationThumbnail(correspondant));
		}
		for (ConversationThumbnail conversation : conversations) {
			messagesReceived.forEach(message -> {
				if (message.getSenderId() == conversation.getCorrespondant())
					conversation.add(message);
			});
			messagesSent.forEach(message -> {
				if (message.getRecipientId() == conversation.getCorrespondant())
					conversation.add(message);
			});
			conversation.sortByDateDesc();
		}
		return conversations;
	}

	private void setNotificationsThumbnails(NotificationType type, NotificationManager notificationM, int userId,
			HttpServletRequest request) throws BLLException {
		List<Notification> notifications = notificationM.getByTypeAndRecipient(type.name(), userId);
		List<NotificationThumbnail> notificationsThumbnails = new ArrayList<>();
		for (Notification notification : notifications) {
			notificationsThumbnails.add(new NotificationThumbnail(notification));
		}
		request.setAttribute(type.name() + "List", notificationsThumbnails);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
