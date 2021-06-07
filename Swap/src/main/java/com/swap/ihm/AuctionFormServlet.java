package com.swap.ihm;

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

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class auctionConfigServlet
 */
@WebServlet("/auction")
public class AuctionFormServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String AUCT_CONF_JSP = "/WEB-INF/AuctionForm.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();
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
		// TODO Use FormCleaner
		// TODO Improve currently illegible ternary affectations
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int categoryId = Integer.valueOf(request.getParameter("category"));
		LocalDate startDate = LocalDate.parse(request.getParameter("start-date"));
		LocalDate endDate = LocalDate.parse(request.getParameter("end-date"));
		int initialPrice = Integer.valueOf(request.getParameter("initial-price"));
		String street = request.getParameter("street").length() > 0 ? request.getParameter("street") : user.getStreet();
		String postcode = request.getParameter("postcode").length() > 0 ? request.getParameter("postcode")
				: user.getPostcode();
		String city = request.getParameter("city").length() > 0 ? request.getParameter("city") : user.getCity();
		Auction auction = new Auction(name, description, startDate, endDate, categoryId, initialPrice,
				user.getUserId());
		PickUpPoint pup = new PickUpPoint(0, street, postcode, city);
		if (request.getParameter("id") == null) {
			createAuction(auction, pup);
		} else {
			auction = getAuctionById(Integer.valueOf(request.getParameter("id")));
			if (auction.getStartDate().isAfter(LocalDate.now())) {
				updateAuction(auction, pup);
			} else {
				// TODO Errors management
			}
		}
		response.sendRedirect(request.getServletContext().getContextPath());
	}

	private void createAuction(Auction auction, PickUpPoint pup) {
		AuctionManager aucmng = new AuctionManager();
		PickUpPointManager pupmng = new PickUpPointManager();
		try {
			aucmng.create(auction);
			pup.setAuctionId(auction.getId());
			pupmng.create(pup);

		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void updateAuction(Auction auction, PickUpPoint pup) {
		AuctionManager aucmng = new AuctionManager();
		PickUpPointManager pupmng = new PickUpPointManager();
		try {
			aucmng.update(auction);
			pup.setAuctionId(auction.getId());
			pupmng.update(pup);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private Auction getAuctionById(int id) {
		AuctionManager aucmng = new AuctionManager();
		Auction auction = null;
		try {
			auction = aucmng.getById(id);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return auction;
	}
}