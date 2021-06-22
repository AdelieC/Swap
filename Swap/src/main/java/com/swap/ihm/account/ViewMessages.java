package com.swap.ihm.account;

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

@WebServlet(urlPatterns = { "/account/messages" })
public class ViewMessages extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_MSG_JSP = "/WEB-INF/ViewMessages.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setNotifications(request);
			sendToJSP(VIEW_MSG_JSP, request, response);
		} catch (BLLException e) {
			// TODO : error 500
			e.printStackTrace();
		}
	}

	private void setNotifications(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		NotificationManager notificationM = new NotificationManager();
		int userId = ((User) session.getAttribute("user")).getUserId();
		List<Notification> receivedList = notificationM.getByRecipient(userId);
		List<Notification> sentList = notificationM.getBySender(userId);
		request.setAttribute("receivedList", receivedList);
		request.setAttribute("SentList", sentList);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
