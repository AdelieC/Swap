package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bll.CategoryManager;
import com.swap.bll.PickUpPointManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.Bid;
import com.swap.bo.Category;
import com.swap.bo.PickUpPoint;
import com.swap.bo.User;

/**
 * Servlet implementation class ViewAuctionServlet
 */
@WebServlet(description = "Handles display of single auction + delete auction", urlPatterns = { "/auction",
		"/auction/delete" })
public class ViewAuctionServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_AUCT_JSP = "/WEB-INF/ViewAuction.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionManager aucmng = new AuctionManager();
		UserManager usmng = new UserManager();
		CategoryManager catmng = new CategoryManager();
		BidManager bidmng = new BidManager();
		PickUpPointManager pupmng = new PickUpPointManager();
		int auctionId = Integer.valueOf(request.getParameter("id"));
		Auction auction = null;
		User seller = null;
		Category cat = null;
		Bid bid = null;
		PickUpPoint pup = null;
		try {
			auction = aucmng.getById(auctionId);
			seller = usmng.getById(auction.getUserId());
			cat = catmng.getById(auction.getCategoryId());
			pup = pupmng.getByAuctionId(auctionId);
			bid = bidmng.getMaxBid(auctionId);
			if (bid != null) {
				User bidder = usmng.getById(bid.getUserId());
				request.setAttribute("bid", bid);
				request.setAttribute("bidder", bidder.getUsername());
			}
			request.setAttribute("auction", auction);
			request.setAttribute("seller", seller);
			request.setAttribute("category", cat);
			request.setAttribute("pickUpPoint", pup);

		} catch (BLLException e) {
			e.printStackTrace();
		}
		sendToJSP(VIEW_AUCT_JSP, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO : Delete auction with session.getAttribute("user").getUserId() check?
		// -> see what I did for user deletion in GetOutServlet, for example?
	}

}
