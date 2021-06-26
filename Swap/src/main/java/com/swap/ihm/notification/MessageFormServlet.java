package com.swap.ihm.notification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.NotificationManager;
import com.swap.bo.BOException;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.FormCleaner;
import com.swap.ihm.IHMException;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/user/message", "/auction/message" })
public class MessageFormServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_PATH = "/Swap/account/notifications";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO : use this function
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("recipientId") == null || request.getParameter("recipientId").isBlank())
				throw new IHMException("Action not permitted");
			createMessage(request);
			response.sendRedirect(SUCCESS_PATH);
		} catch (IHMException e) {
			// TODO : handle "action not permitted" error
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Error 500
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Something went wrong
			e.printStackTrace();
		}
	}

	private void createMessage(HttpServletRequest request) throws BLLException, BOException {
		HttpSession session = request.getSession();
		NotificationManager notificationM = new NotificationManager();
		int recipientId = FormCleaner.cleanId(request.getParameter("recipientId"));
		int senderId = ((User) session.getAttribute("user")).getUserId();
		String content = FormCleaner.cleanText(request.getParameter("content"));
		int auctionId = 0;
		if (request.getParameter("auctionId") != null) {
			auctionId = FormCleaner.cleanId(request.getParameter("auctionId"));
		}
		notificationM.create(new Notification(recipientId, senderId, NotificationType.MESSAGE, content, auctionId));
	}
}
