package com.swap.tests.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Category;
import com.swap.dal.CategoryDAO;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testCategoryDAO
 */
@WebServlet("/testCategoryDAO")
public class testCategoryDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = DAOFactory.getCategoryDAO();
		Category cat1 = new Category("Fashion");
		Category cat2 = new Category("Home & Garden");
		Category cat3 = new Category("Toys");
		Category cat4 = new Category("Electronics");
		Category cat5 = new Category("Motors");
		Category cat6 = new Category("Pets");
		Category cat7 = new Category("Collectables & Art");
		Category cat8 = new Category("Health & Beauty");
		Category cat9 = new Category("Media");
		Category cat10 = new Category("Business & Office Supplies");
		Category cat11 = new Category("Others");
		try {
			// TEST CRUD
			List<Category> list = new ArrayList<Category>();
			categoryDAO.create(cat1);
			categoryDAO.create(cat2);
			categoryDAO.create(cat3);
			categoryDAO.create(cat4);
			categoryDAO.create(cat5);
			categoryDAO.create(cat6);
			categoryDAO.create(cat7);
			categoryDAO.create(cat8);
			categoryDAO.create(cat9);
			categoryDAO.create(cat10);
			categoryDAO.create(cat11);
			list = categoryDAO.read();
			for (Category c : list) {
				System.out.println(c.toString());
			}
			cat3.setLabel("Sports, Hobbies & Leisure");
			categoryDAO.update(cat3);
//			categoryDAO.delete(cat2);
//			categoryDAO.create(cat2);

			// TEST SELECTS
			System.out.println("SELECT BY ID:");
			Category cat = categoryDAO.selectById(42);
			System.out.println(cat.toString());
			System.out.println("SELECT BY LABEL:");
			cat = categoryDAO.selectByLabel("Pets");
			System.out.println(cat.toString());
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
