package com.swap.ihm.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.NotificationManager;
import com.swap.bll.UserManager;
import com.swap.bo.BOException;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.FormCleaner;
import com.swap.ihm.IHMException;
import com.swap.ihm.MotherServlet;
import com.swap.ihm.notification.NotificationType;

@WebServlet(urlPatterns = { "/admin/disable-user" })
public class DisableUserServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";
	private static final UserManager userM = new UserManager();
	private static final NotificationManager notificationM = new NotificationManager();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User admin = (User) session.getAttribute("user");
			if (requestIsAuthorized(request, admin)) {
				int userId = FormCleaner.cleanId(request.getParameter("id"));
				User targetUser = userM.getById(userId);
				String messageContent = "";
				String actionPerformed = "";
				if (requestIsToDisable(request)) {
					userM.disable(targetUser);
					actionPerformed = "disabled";
					messageContent = "Uhoh... Your account was temporarily disabled by an administrator because you didn't respect our terms of use. Don't worry, you can still access your data and manage your ongoing auctions.";
				} else if (requestIsToEnable(request)) {
					userM.enable(targetUser);
					actionPerformed = "enabled";
					messageContent = "Your account has been reactivated! You can now create new auctions and bid on ongoing auctions!";
				}
				notifyUser(userId, admin, messageContent);
				setSuccess(request, targetUser, actionPerformed);
				sendToJSP(OUTCOME_JSP, request, response);
			} else {
				throw new IHMException("Forbidden.");
			}
		} catch (BLLException e) {
			// TODO send to error page = 500
			e.printStackTrace();
		} catch (IHMException e) {
			// TODO send to error page = action not permitted
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean requestIsAuthorized(HttpServletRequest request, User admin) {
		return userIsLoggedIn(request) && admin.isAdmin() && request.getParameter("id") != null && !admin.wasDisabled();
	}

	private boolean requestIsToDisable(HttpServletRequest request) {
		return request.getParameter("submit") != null && request.getParameter("submit").contains("Disable");
	}

	private boolean requestIsToEnable(HttpServletRequest request) {
		return request.getParameter("submit") != null && request.getParameter("submit").contains("Enable");
	}

	private void setSuccess(HttpServletRequest request, User user, String action) {
		String message = "User named " + user.getUsername() + "was successfully " + action + " !";
		request.setAttribute("message", message);
		request.setAttribute("title", "Action completed!");
	}

	private void notifyUser(int userId, User admin, String content) throws BLLException, BOException {
		notificationM.create(new Notification(userId, admin.getUserId(), NotificationType.ADMIN, content));
	}
}
