package com.swap.ihm.notification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bll.NotificationManager;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/account/notification/delete", "/account/notification/update" })
public class ManageNotificationServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_PATH = "/Swap/account/notifications";
	private static final NotificationManager notificationM = new NotificationManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO : use this function
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
