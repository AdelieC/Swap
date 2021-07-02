package com.swap.ihm.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bo.User;

/**
 * Servlet Filter implementation class LoggedInFilter Prevents access to
 * specific pages if user is not logged in
 */
@WebFilter(description = "Filters access to pages depending on userIsLoggedIn value", urlPatterns = { "/user",
		"/account", "/account/edit", "/account/delete", "/account/logout", "/auction/create", "/auction/edit",
		"/auction/cancel", "/auction/bid" })
public class VisitorFilter implements Filter {
	private final static String LOGIN_PATH = "/Swap/login";

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (userIsLoggedIn(request)) {
			chain.doFilter(req, res);
		} else {
			response.sendRedirect(LOGIN_PATH);
		}
	}

	protected boolean userIsLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null
				&& ((User) request.getSession().getAttribute("user")).getUserId() > 0;
	}
}
