package com.swap.ihm.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.BOException;
import com.swap.bo.User;
import com.swap.ihm.FormCleaner;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/account/change-password" })
public class ChangePasswordServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";
	private static final String CHANGE_PASSWORD_JSP = "/WEB-INF/ChangePassword.jsp";
	private static final UserManager userM = new UserManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendToJSP(CHANGE_PASSWORD_JSP, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (passwordsAreAllValid(request, user)) {
				updatePassword(request, user);
				setSuccess(request);
			} else {
				setFailure(request);
			}
			sendToJSP(OUTCOME_JSP, request, response);
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setFailure(HttpServletRequest request) {
		String message = "Sorry, we couldn't change your password. Either your current password was invalid or your new passwords did not match.";
		request.setAttribute("message", message);
		request.setAttribute("title", "Failed to change your password");
	}

	private void setSuccess(HttpServletRequest request) {
		String message = "Your password was successfully changed! Use it to log in next time.";
		request.setAttribute("message", message);
		request.setAttribute("title", "Password changed!");
	}

	private void updatePassword(HttpServletRequest request, User user) throws BOException, BLLException {
		user.changePassword(request.getParameter("password1"));
		userM.updatePassword(user);
	}

	private boolean passwordsAreAllValid(HttpServletRequest request, User user) throws BOException, BLLException {
		String oldPassword = FormCleaner.cleanPassword(request.getParameter("oldPassword"));
		String password1 = FormCleaner.cleanPassword(request.getParameter("password1"));
		String password2 = FormCleaner.cleanPassword(request.getParameter("password2"));
		return oldPasswordIsValid(user, oldPassword) && password1 != null && password1.equals(password2);
	}

	private boolean oldPasswordIsValid(User user, String oldPassword) throws BLLException, BOException {
		return oldPassword != null && userM.couldFetchPasswordData(user) && user.login(oldPassword);
	}
}
