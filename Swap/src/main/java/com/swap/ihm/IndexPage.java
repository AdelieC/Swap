package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.User;

/**
 * Servlet handling index page, homepage (when logged in), logout and delete
 * user
 */
@WebServlet(description = "Handles index page, homepage (when logged in), logout and delete", urlPatterns = { "/",
		"/account/logout", "/account/delete" })
public class IndexPage extends SwapServlet {
	private static final long serialVersionUID = 1L;
	private static final String INDEX_JSP = "/WEB-INF/Index.jsp";
	private static final String HOME_PATH = "/Swap";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendToJSP(INDEX_JSP, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("user") == null)
				throw new IHMException("Action not permitted");
			if ("Delete".equals(request.getParameter("submit"))) {
				deleteCurrentUser(request, session);
			}
			session.setAttribute("user", null);
			session.invalidate();
			response.sendRedirect(HOME_PATH);
		} catch (BLLException e) {
			// TODO send to error page = 500
			e.printStackTrace();
		} catch (IHMException e) {
			// TODO send to error page = action not permitted
			e.printStackTrace();
		}
	}

	private void deleteCurrentUser(HttpServletRequest request, HttpSession session) throws BLLException {
		UserManager userM = new UserManager();
		int userId = FormCleaner.cleanId(request.getParameter("userId"));
		System.out.println("Deleting user " + userId);
		if (((User) session.getAttribute("user")).getUserId() == userId) {
			System.out.println("Conditions to delete are met.");
			userM.delete(userId);
		}
	}

}
