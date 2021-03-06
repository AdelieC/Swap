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

/**
 * Servlet to display and process user login OR delete account
 */
@WebServlet(description = "Handles login or register page depending on session variable", urlPatterns = { "/login" })
public class LoginServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_JSP = "/WEB-INF/Login.jsp";
	private static final String HOME_PATH = "/Swap";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (userIsLoggedIn(request)) {
			request.setAttribute("previousPath", request.getHeader("referer"));
			redirectBecauseLoggedIn(request, response);
		} else {
			request.setAttribute("previousPath", request.getHeader("referer"));
			sendToJSP(LOGIN_JSP, request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			UserManager userM = new UserManager();
			String username = FormCleaner.cleanUsername(request.getParameter("username"));
			String password = FormCleaner.cleanPassword(request.getParameter("password"));
			if (username == null || username == null) {
				doGet(request, response);
			}
			User user = new User(username);
			if (userM.couldFetchPasswordData(user)) {
				request.setAttribute("username", username);
				if (user.login(password)) {
					user = userM.getByUsername(username);
					session.setAttribute("user", user);
					redirectBecauseLoggedIn(request, response);
				} else {
					doGet(request, response);
				}
			}
		} catch (BLLException e) {
			// TODO Redirect to error page : 500
			e.printStackTrace();
		} catch (BOException e) {
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
	protected void redirectBecauseLoggedIn(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String previousPath = request.getParameter("previousURL");
		String redirectPath = (previousPath == null ? HOME_PATH : previousPath);
		response.sendRedirect(redirectPath);
	}

}
