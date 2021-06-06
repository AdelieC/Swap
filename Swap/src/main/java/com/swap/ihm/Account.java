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
@WebServlet(description = "Handles showing profile page, deleting account or logging out", urlPatterns = { "/account",
		"/user" })
public class Account extends SwapServlet {
	private static final long serialVersionUID = 1L;
	private static final String PROFILE_JSP = "/WEB-INF/Profile.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = null;
		if (request.getRequestURI().contains("user") && request.getParameter("id") != null) {
			UserManager userM = new UserManager();
			try {
				user = userM.getById(Integer.parseInt(request.getParameter("id")));
			} catch (NumberFormatException | BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (user == null) {
				// TODO : handle 404
			} else {
				request.setAttribute("user", user);
			}
		} else {
			request.setAttribute("user", session.getAttribute("user"));
		}
		sendToJSP(PROFILE_JSP, request, response);
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
