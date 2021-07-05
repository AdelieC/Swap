package com.swap.ihm.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.User;
import com.swap.ihm.MotherServlet;

/**
 * Servlet handling index of all users, only accessible by admin
 */
@WebServlet(description = "Handles index page or homepage (when logged in)", urlPatterns = { "/admin/all-users" })
public class ViewAllUsersServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_USERS_JSP = "/WEB-INF/ViewAllUsers.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setThumbnails(request);
			sendToJSP(VIEW_USERS_JSP, request, response);
		} catch (BLLException e) {
			// TODO send to error page 500
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO : complete
	}

	private void setThumbnails(HttpServletRequest request) throws BLLException {
		List<User> users = new ArrayList<>();
		List<UserThumbnail> thumbnails = new ArrayList<>();
		UserManager userM = new UserManager();
		users = userM.getAll();
		thumbnails = getThumbnailList(users);
		request.setAttribute("thumbnails", thumbnails);
	}

	private List<UserThumbnail> getThumbnailList(List<User> users) {
		List<UserThumbnail> thumbnails = new ArrayList<>();
		users.forEach(user -> thumbnails.add(getThumbnail(user)));
		return thumbnails;
	}

	private UserThumbnail getThumbnail(User user) {
		return new UserThumbnail(user.getUserId(), user.getUsername(), user.getEmail(), user.isAdmin());
	}

}
