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
 * Servlet to display and process user login
 */
@WebServlet(description = "Handles login or register page depending on session variable", urlPatterns = { "/login" })
public class Login extends SwapServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_PATH = "/WEB-INF/Login.jsp";
	private static final String DEFAULT_SUCCESS_PATH = "/Swap";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("previousPath", request.getHeader("referer"));
		if (userIsLoggedIn(request)) {
			redirectWhenLoggedIn(request, response);
		} else {
			sendToJSP(LOGIN_PATH, request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			UserManager userM = new UserManager();
			String username = FormCleaner.cleanInputText(request.getParameter("username"), 30);
			String password = FormCleaner.cleanInputPassword(request.getParameter("password"));

			user = userM.login(username, password);

			if (user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				redirectWhenLoggedIn(request, response);
			} else {
				request.setAttribute("username", username);
				doGet(request, response);
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Redirects to last page user visited before /login OR to index page if login
	 * was the first page visited.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void redirectWhenLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String previousPath = request.getParameter("previousURL");
		String redirectPath = (previousPath == null ? DEFAULT_SUCCESS_PATH : previousPath);
		response.sendRedirect(redirectPath);
	}

}
