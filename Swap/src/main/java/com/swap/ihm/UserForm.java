package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.BOException;
import com.swap.bo.User;

/**
 * Servlet implementation class UserForm
 */
@WebServlet(description = "Handles register or edit profile page depending on session variable", urlPatterns = {
		"/register", "/my-profile/edit" })
public class UserForm extends SwapServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERFORM_PATH = "/WEB-INF/UserForm.jsp";
	private static final String DEFAULT_SUCCESS_PATH = "/WEB-INF/SuccessfullLogin.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("currentUser") == null) {
			this.getServletContext().getRequestDispatcher(LOGIN_PATH).forward(request, response);
		} else {
			// TODO : response.sendRedirect(DEFAULT_SUCCESS_PATH); change fastforward into
			// this!
			this.getServletContext().getRequestDispatcher(DEFAULT_SUCCESS_PATH).forward(request, response);
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
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));

			user = userM.login(user.getUsername(), user.getPassword());

			if (userM.isValid(user)) {

				session.setAttribute("user", user);
				redirectWhenLoggedIn(request, response);
			} else {
				request.setAttribute("username", user.getUsername());
				doGet(request, response);
			}
		} catch (BLLException | BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
