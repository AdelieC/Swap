package com.swap.tests.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.PickUpPoint;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.PickUpPointDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testPickUpPointDAO
 */
@WebServlet("/testPickUpPointDAO")
public class testPickUpPointDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO CHANGE FOLLOLWING STRINGS FOR VALUES CORRESPONDING TO DATABASE ENTRIES :
		String city = "";
		String postcode = "";
		String street = "";
		PickUpPointDAO pupDAO = DAOFactory.getPickUpPointDAO();
		PickUpPoint pup1 = new PickUpPoint(1, "Sesame Street", "007", "Supercity");
		PickUpPoint pup2 = new PickUpPoint(1, "Market Square", "089YU", "City");
		try {
			// TEST CRUD
			pupDAO.create(pup1);
			pupDAO.create(pup2);
			pupDAO.read();
			pupDAO.delete(pup2);
			pupDAO.create(pup2);

			// TEST SELECTS
			System.out.println("SELECT BY ID:");
			PickUpPoint pup = pupDAO.selectById(1);
			List<PickUpPoint> list = new ArrayList<PickUpPoint>();
			System.out.println(pup.toString());
			System.out.println("SELECT BY ITEM ID:");
			pup = pupDAO.selectByItemId(2);
			for (PickUpPoint p : list) {
				System.out.println(p.toString());
			}
			System.out.println("SELECT BY CITY:");
			list = pupDAO.selectByCity(city);
			for (PickUpPoint p : list) {
				System.out.println(p.toString());
			}
			System.out.println("SELECT BY STREET:");
			list = pupDAO.selectByStreet(street);
			for (PickUpPoint p : list) {
				System.out.println(p.toString());
			}
			System.out.println("SELECT BY POSTCODE:");
			list = pupDAO.selectByPostcode(postcode);
			for (PickUpPoint p : list) {
				System.out.println(p.toString());
			}
		} catch (

		DALException e) {
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
