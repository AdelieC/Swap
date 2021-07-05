package com.swap.ihm.notification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.swap.bll.BLLException;
import com.swap.bll.NotificationManager;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/account/notification/delete", "/account/notification/update" })
public class ManageNotificationServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_PATH = "/Swap/account/notifications";
	private static final NotificationManager notificationM = new NotificationManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("type") != null && !request.getParameter("type").isEmpty()) {
				HttpSession session = request.getSession();
				int userId = ((User) session.getAttribute("user")).getUserId();
				String type = request.getParameter("type");
				List<Notification> notificationsRead = notificationM.getByTypeAndRecipient(type, userId);
				if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
					int senderId = Integer.parseInt(request.getParameter("id"));
					notificationsRead.removeIf(n -> n.getSenderId() != senderId);
				}
				for (Notification n : notificationsRead)
					notificationM.markAsRead(n);
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
				int id = Integer.parseInt(request.getParameter("id"));
				notificationM.delete(id);
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
