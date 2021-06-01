package com.swap.tests.dal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Auction;
import com.swap.dal.AuctionDAO;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testAuctionDAO
 */
@WebServlet("/testAuctionDAO")
public class testAuctionDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionDAO auctionDAO = DAOFactory.getAuctionDAO();
		Auction auction1 = new Auction("Vintage shirt", "Collector hawaiian vintage shirt", LocalDate.now(),
				LocalDate.now(), 17, 5, 1);
		Auction auction2 = new Auction("Basketball", "Old basketball", LocalDate.now(), LocalDate.now(), 19, 2, 2);
		Auction auction3 = new Auction("Pikachu figurine", "It's shiny", LocalDate.now(), LocalDate.now(), 20, 4, 1);
		try {
			// TEST CRUD
			auctionDAO.create(auction1);
			auctionDAO.create(auction2);
			auctionDAO.create(auction3);
			auctionDAO.read();
			auction3.setSalePrice(5);
			auctionDAO.update(auction3);
			auctionDAO.delete(auction2);

			// TEST SELECTS
			System.out.println("SELECT BY ID:");
			Auction auction = auctionDAO.selectById(1);
			List<Auction> list = new ArrayList<Auction>();
			System.out.println(auction.toString());
			System.out.println("SELECT BY CATEGORY:");
			list = auctionDAO.selectByCategory(1);
			for (Auction i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY DATE:");
			// TODO Improve this test
			list = auctionDAO.selectByDate(LocalDate.now());
			for (Auction i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY NAME:");
			list = auctionDAO.selectByName("name");
			for (Auction i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY KEYWORD:");
			list = auctionDAO.selectByKeyword("It");
			for (Auction i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY PRICE:");
			list = auctionDAO.selectByPrice(4);
			for (Auction i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY USER:");
			list = auctionDAO.selectByUser(2);
			for (Auction i : list) {
				System.out.println(i.toString());
			}
			System.out.println("SELECT BY USER AND DATE:");
			// TODO Update date test
			list = auctionDAO.selectByUserAndDate(1, LocalDate.now());
			for (Auction i : list) {
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
