package com.swap.tests.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.User;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testUserDAO
 */
@WebServlet("/addUsersDAO")
public class addUsersDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = DAOFactory.getUserDAO();
		User user1 = new User("Bloony", "BLOON", "Georges", "gbloon@mail.com", "01.23.45.67.89", "Sesame street",
				"XXXXXX", "Supercity", "password", 0, false);
		User user2 = new User("jim32", "MICHAEL", "Jim", "jim@mail.com", "01.23.45.67.99", "Main street", "XXXXXX",
				"Supercity", "password", 0, false);
		User user3 = new User("sweetyPie", "HOP", "Leah", "leah.hop@mail.com", "01.23.45.77.89", "Sesame street",
				"XXXXXX", "Supercity", "password", 0, false);
		User user4 = new User("Virginie", "TRAN", "Virginie", "vivi78@mail.com", "01.23.44.67.89", "Main Street",
				"XXXXXZ", "Mehcity", "password", 0, false);
		User user5 = new User("Sasuke", "TRAORE", "Demba", "dtraore@mail.net", "01.22.45.67.89", "21, private drive",
				"XXXXXY", "Ouchcity", "password", 0, false);
		try {
			// TEST CRUD
			List<User> list = new ArrayList<User>();
			userDAO.create(user1);
			userDAO.create(user2);
			userDAO.create(user3);
			userDAO.create(user4);
			userDAO.create(user5);
			list = userDAO.read();
			for (User c : list) {
				System.out.println(c.toString());
			}

			// TEST SELECTS
			System.out.println("SELECT BY ID:");
			User user = userDAO.selectById(4);
			System.out.println(user.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
