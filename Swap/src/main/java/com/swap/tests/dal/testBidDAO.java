package com.swap.tests.dal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Bid;
import com.swap.dal.BidDAO;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testBidDAO
 */
@WebServlet("/testBidDAO")
public class testBidDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BidDAO bidDAO = DAOFactory.getBidDAO();
		Bid bid1 = new Bid(1, 2, 3, LocalDate.now());
		System.out.println(bid1.toString());
		try {
			// TEST CRUD
			bidDAO.create(bid1);

			// TEST SELECTS
			System.out.println("SELECT BY ID:");
			Bid bid = bidDAO.selectById(1);
			List<Bid> list = new ArrayList<Bid>();
			System.out.println(bid.toString());
			System.out.println("SELECT BY AUCTION ID:");
			list = bidDAO.selectByAuctionId(1);
			for (Bid b : list) {
				System.out.println(b.toString());
			}
			System.out.println("SELECT BY AUCTION ID:");
			list = bidDAO.selectByAuctionId(2);
			for (Bid b : list) {
				System.out.println(b.toString());
			}
			System.out.println("SELECT BY PRICE:");
			list = bidDAO.selectByPrice(3);
			for (Bid b : list) {
				System.out.println(b.toString());
			}
			System.out.println("SELECT BY DATE:");
			list = bidDAO.selectByDate(bid.getDate());
			for (Bid b : list) {
				System.out.println(b.toString());
			}
			System.out.println("SELECT BY USER ID:");
			list = bidDAO.selectByUser(1);
			for (Bid b : list) {
				System.out.println(b.toString());
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
