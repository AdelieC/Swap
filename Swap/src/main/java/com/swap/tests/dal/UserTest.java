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
 * Servlet to test DAL user in console
 */
@WebServlet(description = "Tests for UserDAO", urlPatterns = { "/UserTest" })
public class UserTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idTest;
		String usernameTest;
		String cityTest;
		String emailTest;
		User validUser1 = new User("Minho", "Lee", "Min Ho", "leeminho@gmail.com", "+123 145 575 589", "54 king street",
				"20000", "Seoul", "PasswordBId0n1!", 1000, false);
		User validUser2 = new User("Seungi", "Lee", "Seung Gi", "leeseunggi@gmail.com", "+123 546 575 589",
				"2 monkey street", "30000", "Busan", "PasswordBId0n2!", 1000, false);
		User validUser3 = null;
		User validUser4 = null;
		User validAdmin = new User("Minki", "Lee", "Min Ki", "leeminki@gmail.com", "+123 258 575 589", "70 king street",
				"20000", "Seoul", "PasswordBId0n4!", 1000, true);
		List<User> users = new ArrayList<>();

		try {
			UserDAO userDAO = DAOFactory.getUserDAO();
			// TEST CRUD
			userDAO.create(validUser1);
			userDAO.create(validUser2);
			userDAO.create(validAdmin);
			users = userDAO.read();
			validUser1 = users.get(0);
			validUser2 = users.get(1);
			validUser3 = users.get(2);
			System.out.println("---------------------------");
			System.out.println("CREATED USERS");
			users.forEach(x -> System.out.println(x));
			System.out.println("---------------------------");
			System.out.println("UPDATED USERS");
			validUser1.setPassword("newPa$$w0rd");
			userDAO.update(validUser1);
			users = userDAO.read();
			users.forEach(x -> System.out.println(x));
			System.out.println("---------------------------");
			System.out.println("DELETED USERS");
			userDAO.create(validUser3);
			userDAO.delete(validUser2);
			userDAO.delete(validUser3.getUserId());
			users = userDAO.read();
			users.forEach(x -> System.out.println(x));
			System.out.println("---------------------------");
			System.out.println();

			// TEST SELECTS
			System.out.println("---------------------------");
			System.out.println("SELECT BY ID");
			idTest = users.get(0).getUserId();
			validUser4 = userDAO.selectById(idTest);
			System.out.println(validUser4);
			System.out.println("---------------------------");
			System.out.println("SELECT BY USERNAME");
			usernameTest = users.get(0).getUsername();
			validUser4 = userDAO.selectByUsername(usernameTest);
			System.out.println(validUser4);
			System.out.println("---------------------------");
			System.out.println("SELECT BY EMAIL");
			emailTest = users.get(0).getEmail();
			validUser4 = userDAO.selectByEmail(emailTest);
			System.out.println(validUser4);
			System.out.println("---------------------------");
			System.out.println("SELECT BY CITY");
			cityTest = users.get(0).getCity();
			users = userDAO.selectByCity(cityTest);
			users.forEach(x -> System.out.println(x));
			System.out.println("---------------------------");
			System.out.println("SELECT ALL ADMINS");
			users = userDAO.selectAllAdmins();
			users.forEach(x -> System.out.println(x));
			System.out.println("---------------------------");

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
