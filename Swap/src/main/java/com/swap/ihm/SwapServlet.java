package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@SuppressWarnings("serial")
public abstract class SwapServlet extends HttpServlet {

	protected boolean userIsLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}

	protected void sendToJSP(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

}
