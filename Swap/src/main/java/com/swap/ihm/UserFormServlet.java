package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.User;

/**
 * Servlet handling creation and edition of user profile
 */
@WebServlet(description = "Handles creation and edition of user profile", urlPatterns = { "/register",
		"/account/edit" })
public class UserFormServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERFORM_JSP = "/WEB-INF/UserForm.jsp";
	private static final String SUCCESS_PATH = "/Swap/account";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendToJSP(USERFORM_JSP, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (userIsLoggedIn(request)) {
			editProfile(request, response);
		} else {
			createProfile(request, response);
		}
	}

	private void createProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, String> inputs = getInputValues(request);
			Map<String, String> errors = getErrors(inputs);
			HttpSession session = request.getSession();
			User user = new User(inputs.get("username"), inputs.get("lastName"), inputs.get("firstName"),
					inputs.get("email"), inputs.get("telephone"), inputs.get("street"), inputs.get("postcode"),
					inputs.get("city"), inputs.get("password"), 0, false);
			session.setAttribute("user", user);
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				doGet(request, response);
			} else {
				UserManager userM = new UserManager();
				userM.create(user);
				// Following line = to get new user id!
				session.setAttribute("user", userM.getByUsername(inputs.get("username")));
				response.sendRedirect(SUCCESS_PATH);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, String> inputs = getInputValues(request);
			Map<String, String> errors = getErrors(inputs);
			HttpSession session = request.getSession();
			User user = new User(((User) session.getAttribute("user")).getUserId(), inputs.get("username"),
					inputs.get("lastName"), inputs.get("firstName"), inputs.get("email"), inputs.get("telephone"),
					inputs.get("street"), inputs.get("postcode"), inputs.get("city"), inputs.get("password"),
					((User) session.getAttribute("user")).getBalance(),
					((User) session.getAttribute("user")).isAdmin());
			session.setAttribute("user", user);
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				doGet(request, response);
			} else {
				UserManager userM = new UserManager();
				userM.update(user);
				// No need to reset session user to get id and modifs
				// -> user set around line 79 is the right one
				response.sendRedirect(SUCCESS_PATH);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	private Map<String, String> getInputValues(HttpServletRequest request) {
		Map<String, String> inputValues = new HashMap<>();

		String password1 = FormCleaner.cleanPassword(request.getParameter("password1"));
		String password2 = FormCleaner.cleanPassword(request.getParameter("password2"));
		if (password1 != null && !password1.equals(password2)) {
			password1 = null;
		}

		inputValues.put("username", FormCleaner.cleanUsername(request.getParameter("username")));
		inputValues.put("password", FormCleaner.encode(password1));
		inputValues.put("lastName", FormCleaner.cleanName(request.getParameter("lastName")));
		inputValues.put("firstName", FormCleaner.cleanName(request.getParameter("firstName")));
		inputValues.put("email", FormCleaner.cleanEmail(request.getParameter("email")));
		inputValues.put("telephone", FormCleaner.cleanTel(request.getParameter("telephone")));
		inputValues.put("street", FormCleaner.cleanStreet(request.getParameter("street")));
		inputValues.put("postcode", FormCleaner.cleanPostcode(request.getParameter("postcode")));
		inputValues.put("city", FormCleaner.cleanName(request.getParameter("city")));

		return inputValues;
	}

	private Map<String, String> getErrors(Map<String, String> inputs) {
		Map<String, String> errors = new HashMap<>();
		inputs.forEach((x, y) -> {
			System.out.println(x + " : " + y);
			if (y == null)
				errors.put(x, FormError.getError(x).toString());
		});
		return errors;
	}
}
