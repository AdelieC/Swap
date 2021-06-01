package com.swap.tests.dal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Item;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.ItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testItemDAO
 */
@WebServlet("/testItemDAO")
public class testItemDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ItemDAO itemDAO = DAOFactory.getItemDAO();
		Item item1 = new Item("Vintage shirt", "Collector hawaiian vintage shirt", LocalDate.now(), LocalDate.now(), 17,
				5, 1);
		Item item2 = new Item("Basketball", "Old basketball", LocalDate.now(), LocalDate.now(), 19, 2, 2);
		Item item3 = new Item("Pikachu figurine", "It's shiny", LocalDate.now(), LocalDate.now(), 20, 4, 1);
		try {
			// TEST CRUD
			itemDAO.create(item1);
			itemDAO.create(item2);
			itemDAO.create(item3);
			itemDAO.read();
			item3.setSalePrice(5);
			itemDAO.update(item3);
			itemDAO.delete(item2);

			// TEST SELECTS
			System.out.println("SELECT BY ID:");
			Item item = itemDAO.selectById(1);
			List<Item> list = new ArrayList<Item>();
			System.out.println(item.toString());
			System.out.println("SELECT BY CATEGORY:");
			list = itemDAO.selectByCategory(1);
			for (Item i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY DATE:");
			// TODO Improve this test
			list = itemDAO.selectByDate(LocalDate.now());
			for (Item i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY NAME:");
			list = itemDAO.selectByName("name");
			for (Item i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY KEYWORD:");
			list = itemDAO.selectByKeyword("It");
			for (Item i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY PRICE:");
			list = itemDAO.selectByPrice(4);
			for (Item i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY USER:");
			list = itemDAO.selectByUser(2);
			for (Item i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY USER AND DATE:");
			// TODO Update date test
			list = itemDAO.selectByUserAndDate(1, LocalDate.now());
			for (Item i : list) {
				System.out.println(i.toString());
			}
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
