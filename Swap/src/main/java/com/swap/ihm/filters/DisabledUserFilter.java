package com.swap.ihm.filters;

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

import com.swap.bo.User;

/**
 * Servlet Filter implementation class DisabledFilter
 */
@WebFilter(urlPatterns = { "/admin/notify", "/admin/all-users", "/auction", "/auction/cancel", "/user/message",
		"/auction/message", "/auction/bid" })
public class DisabledUserFilter implements Filter {
	private final static String HOME_PATH = "/Swap/";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && !((User) session.getAttribute("user")).wasDisabled()) {
			chain.doFilter(req, res);
		} else {
			response.sendRedirect(HOME_PATH);
		}
	}

}
