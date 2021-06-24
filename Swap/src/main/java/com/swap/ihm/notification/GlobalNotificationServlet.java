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
import com.swap.bll.UserManager;
import com.swap.bo.BOException;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.FormCleaner;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/admin/notify" })
public class GlobalNotificationServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_PATH = "/Swap/account/messages";
	private static final String GLOBAL_NOTIF_JSP = "/WEB-INF/GlobalNotificationForm.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendToJSP(GLOBAL_NOTIF_JSP, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			createAllGlobalNotifications(request);
			response.sendRedirect(SUCCESS_PATH);
		} catch (BLLException e) {
			// TODO error 500
			e.printStackTrace();
		} catch (BOException e) {
			// TODO error 403 forbidden
			e.printStackTrace();
		}
	}

	private void createAllGlobalNotifications(HttpServletRequest request) throws BLLException, BOException {
		HttpSession session = request.getSession();
		NotificationManager notificationM = new NotificationManager();
		UserManager userM = new UserManager();
		int senderId = ((User) session.getAttribute("user")).getUserId();
		String content = FormCleaner.cleanText(request.getParameter("content"));
		List<User> users = userM.getAll();
		for (User user : users)
			notificationM.create(new Notification(user.getUserId(), senderId, NotificationType.ADMIN, content));
	}
}
