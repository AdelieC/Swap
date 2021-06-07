package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.User;

/**
 * Servlet implementation class Account
 */
@WebServlet(description = "Handles showing profile page", urlPatterns = { "/account", "/user" })
public class AccountServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String PROFILE_JSP = "/WEB-INF/Profile.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = null;
			if (request.getRequestURI().contains("user") && request.getParameter("id") != null) {
				UserManager userM = new UserManager();
				user = userM.getById(Integer.parseInt(request.getParameter("id")));
			} else {
				user = (User) session.getAttribute("user");
			}
			request.setAttribute("targetUser", user);
			sendToJSP(PROFILE_JSP, request, response);
		} catch (NumberFormatException | BLLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
