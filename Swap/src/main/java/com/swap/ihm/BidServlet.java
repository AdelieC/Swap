package com.swap.ihm;

import java.io.IOException;
import java.time.LocalDate;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.BOException;
import com.swap.bo.Bid;
import com.swap.bo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/auction/bid")
public class BidServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String BID_JSP = "/WEB-INF/BidDone.jsp";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendToJSP(BID_JSP, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Bid bid;
		User user;
		int offer = Integer.valueOf(request.getParameter("offer"));
		int auctionId = Integer.valueOf(request.getParameter("id"));
		user = ((User) session.getAttribute("user"));
		if (isValid(offer, auctionId) && user.getBalance() > offer) {
			bid = createBid(auctionId, offer, user);
			request.setAttribute("bid", bid);
		} else {
			// TODO : add error msg "insufficient funds"
		}
		doGet(request, response);
	}

	private boolean isValid(int offer, int auctionId) {
		Auction auction = getAuction(auctionId);
		if (offer > auction.getSalePrice() && auction.getStatus().equals(AuctionStatus.ONGOING.getStatus())) {
			return true;
		}
		return false;
	}

	private Auction getAuction(int auctionId) {
		Auction auction = null;
		AuctionManager aucmng = new AuctionManager();
		try {
			auction = aucmng.getById(auctionId);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return auction;
	}

	private Bid createBid(int auctionId, int offer, User user) {
		BidManager bidmng = new BidManager();
		UserManager usmng = new UserManager();
		Bid bid = null, previousBid;
		try {

			previousBid = bidmng.getMaxBid(auctionId);
			bid = new Bid(user.getUserId(), auctionId, offer, LocalDate.now());
			bidmng.create(bid);
			user.setBalance(user.getBalance() - offer);
			usmng.update(user);
			if (previousBid != null) {
				refundPreviousBidder(previousBid);
			}
			updateAuction(auctionId, offer);
		} catch (BLLException | BOException e) {
			// TODO : send to 500 page
			e.printStackTrace();
		}
		return bid;
	}

	private void refundPreviousBidder(Bid previousBid) {
		UserManager usmng = new UserManager();
		try {
			User previousBidder = usmng.getById(previousBid.getUserId());
			previousBidder.setBalance(previousBidder.getBalance() + previousBid.getBidPrice());
			usmng.update(previousBidder);
		} catch (BLLException | BOException e) {
			// TODO : send to 500 page
			e.printStackTrace();
		}
	}

	private void updateAuction(int auctionId, int offer) {
		AuctionManager aucmng = new AuctionManager();
		try {
			Auction auction = aucmng.getById(auctionId);
			auction.setSalePrice(offer);
			aucmng.update(auction);
		} catch (BLLException e) {
			// TODO : send to 500 page
			e.printStackTrace();
		}
	}
}
