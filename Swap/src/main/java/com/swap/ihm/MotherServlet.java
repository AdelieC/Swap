package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bo.User;

@SuppressWarnings("serial")
public abstract class MotherServlet extends HttpServlet {

	protected boolean userIsLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null
				&& ((User) request.getSession().getAttribute("user")).getUserId() > 0;
	}

	protected void sendToJSP(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}
