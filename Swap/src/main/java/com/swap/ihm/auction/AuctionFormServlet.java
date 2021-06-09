package com.swap.ihm.auction;

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
import com.swap.ihm.AuctionStatus;
import com.swap.ihm.MotherServlet;

/**
 * Servlet implementation class auctionConfigServlet
 */
@WebServlet(description = "Handles creation of a single auction", urlPatterns = { "/auction" })
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String title = "Create auction";
		List<Category> categorieslist = new ArrayList<Category>();
		CategoryManager catmng = new CategoryManager();
		PickUpPoint pup = null;
		if (request.getParameter("id") != null) {
			title = "Update auction";
			int id = Integer.valueOf(request.getParameter("id"));
			Auction auction = getAuctionById(id);
			pup = getPUPByAuctionId(id);
			request.setAttribute("auction", auction);

		} else {
			pup = new PickUpPoint(0, user.getStreet(), user.getPostcode(), user.getCity());
		}
		request.setAttribute("pickUpPoint", pup);
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
		if (request.getParameter("auctionId").equals("")) {
			createAuction(auction, pup);
		} else {
			auction.setId(Integer.valueOf(request.getParameter("auctionId")));
			if (auction.getStatus().equals(AuctionStatus.CREATED.getStatus())) {
				updateAuction(auction, pup);
				request.setAttribute("cancellable", true);
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
		try {
			aucmng.update(auction);
			updatePickUpPoint(pup, auction.getId());
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void updatePickUpPoint(PickUpPoint pup, int auctionId) {
		PickUpPointManager pupmng = new PickUpPointManager();
		pup.setAuctionId(auctionId);
		pup.setId(getPupIdWithAuctionId(auctionId));
		try {
			pupmng.update(pup);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private Auction getAuctionById(int auctionId) {
		AuctionManager aucmng = new AuctionManager();
		Auction auction = null;
		try {
			auction = aucmng.getById(auctionId);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return auction;
	}

	private PickUpPoint getPUPByAuctionId(int id) {
		PickUpPointManager pupmng = new PickUpPointManager();
		PickUpPoint pup = null;
		try {
			pup = pupmng.getById(id);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return pup;
	}

	private int getPupIdWithAuctionId(int auctionId) {
		PickUpPoint pup = null;
		int id = 0;
		pup = getPUPByAuctionId(id);
		id = pup.getId();
		return id;
	}
}