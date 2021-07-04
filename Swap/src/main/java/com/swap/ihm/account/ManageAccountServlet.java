package com.swap.ihm.account;

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
import com.swap.bo.BOException;
import com.swap.bo.User;
import com.swap.ihm.FormCleaner;
import com.swap.ihm.FormError;
import com.swap.ihm.MotherServlet;

/**
 * Servlet handling creation and edition of user profile
 */
@WebServlet(urlPatterns = { "/register", "/account/edit" })
public class ManageAccountServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String MANAGE_ACCOUNT_JSP = "/WEB-INF/ManageAccount.jsp";
	private static final String SUCCESS_PATH = "/Swap/account";
	private static final UserManager userM = new UserManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendToJSP(MANAGE_ACCOUNT_JSP, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (userIsLoggedIn(request)) {
				editProfile(request, response);
			} else {
				createProfile(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BOException {
		try {
			Map<String, String> inputs = getInputValues(request);
			Map<String, String> errors = getErrors(inputs);
			HttpSession session = request.getSession();
			User user = new User(inputs.get("username"), inputs.get("lastName"), inputs.get("firstName"),
					inputs.get("email"), inputs.get("telephone"), inputs.get("street"), inputs.get("postcode"),
					inputs.get("city"), inputs.get("password"));
			session.setAttribute("user", user);
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				doGet(request, response);
			} else {
				userM.create(user);
				session.setAttribute("user", user);
				response.sendRedirect(SUCCESS_PATH);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BOException {
		try {
			HttpSession session = request.getSession();
			User currentUser = ((User) session.getAttribute("user"));
			Map<String, String> inputs = getInputValues(request);
			Map<String, String> errors = getErrors(inputs);
			User user = new User(currentUser.getUserId(), inputs.get("username"), inputs.get("lastName"),
					inputs.get("firstName"), inputs.get("email"), inputs.get("telephone"), inputs.get("street"),
					inputs.get("postcode"), inputs.get("city"));
			session.setAttribute("user", user);
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				doGet(request, response);
			} else {
				userM.update(user);
				response.sendRedirect(SUCCESS_PATH);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	private Map<String, String> getInputValues(HttpServletRequest request) {
		Map<String, String> inputValues = new HashMap<>();
		if (!userIsLoggedIn(request)) {
			String password1 = FormCleaner.cleanPassword(request.getParameter("password1"));
			String password2 = FormCleaner.cleanPassword(request.getParameter("password2"));
			if (password1 != null && !password1.equals(password2)) {
				password1 = null;
			}
			inputValues.put("password", password1);
		}
		inputValues.put("username", FormCleaner.cleanUsername(request.getParameter("username")));
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
