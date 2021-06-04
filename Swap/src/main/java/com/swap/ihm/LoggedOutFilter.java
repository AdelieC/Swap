package com.swap.ihm;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class LoggedInFilter Prevents access to
 * specific pages if user is not logged in
 */
@WebFilter(description = "Filters access to pages depending on session.loggedIn value", urlPatterns = { "/my-homepage",
		"/account", "/account/my-profile", "/account/my-profile/edit", "/create-new-auction", "/edit-auction" })
public class LoggedOutFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null)
			response.sendRedirect("/login");
		chain.doFilter(request, response);
	}

}
