package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bll.PickUpPointManager;
import com.swap.bo.Auction;
import com.swap.bo.Category;
import com.swap.bo.PickUpPoint;
import com.swap.bo.User;

/**
 * Servlet implementation class auctionConfigServlet
 */
@WebServlet("/auction")
public class AuctionConfigServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String AUCT_CONF_JSP = "/WEB-INF/AuctionConfig.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// TODO DEAL WITH USER SESSION
		String title = "Create auction";
		List<Category> categorieslist = new ArrayList<Category>();
		CategoryManager catmng = new CategoryManager();
		AuctionManager aucmng = new AuctionManager();
		if (request.getParameter("id") != null) {
			title = "Update auction";
			int id = Integer.valueOf(request.getParameter("id"));
			try {
				Auction auction = aucmng.getById(id);
				request.setAttribute("auction", auction);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		try {
			categorieslist = catmng.getAll();
			request.setAttribute("categoriesList", categorieslist);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		request.setAttribute("title", title);
		sendToJSP(AUCT_CONF_JSP, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// Just added session user + changed path + mother class. Did not check code
		AuctionManager aucmng = new AuctionManager();
		PickUpPointManager pupmng = new PickUpPointManager();
		String name = RequestVerification.getString(request, "name");
		String description = RequestVerification.getString(request, "description");
		int categoryId = RequestVerification.getInteger(request, "category");
		LocalDate startDate = RequestVerification.getLocalDate(request, "start-date");
		LocalDate endDate = RequestVerification.getLocalDate(request, "end-date");
		int initialPrice = RequestVerification.getInteger(request, "initial-price");
		String street = RequestVerification.getString(request, "street").length() > 0
				? RequestVerification.getString(request, "street")
				: user.getStreet();
		String postcode = RequestVerification.getString(request, "postcode").length() > 0
				? RequestVerification.getString(request, "postcode")
				: user.getPostcode();
		String city = RequestVerification.getString(request, "city").length() > 0
				? RequestVerification.getString(request, "city")
				: user.getCity();
		Auction auction = new Auction(name, description, startDate, endDate, categoryId, initialPrice,
				user.getUserId());
		PickUpPoint pup = new PickUpPoint(0, street, postcode, city);

		if (request.getAttribute("auction") != null) {
			// UNREACHABLE BLOCK
			// Invalid condition
			// TODO
			auction = (Auction) request.getAttribute("auction");
			try {
				aucmng.update(auction);
				if (!pup.equals(request.getAttribute("pickUpPoint"))) {
					pup.setAuctionId(auction.getId());
					pupmng.update(pup);
				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				aucmng.create(auction);
				pup.setAuctionId(auction.getId());
				pupmng.create(pup);

			} catch (BLLException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getServletContext().getContextPath());
		}
	}
}