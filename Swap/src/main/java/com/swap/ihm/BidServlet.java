package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/bid")
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
		// TODO Get auctionID -> in hidden input in the bid form?
		// TODO Refactorize and test ;-)
		// ?? Put managers inside the try block ?? -> no need
		UserManager usmng = new UserManager();
		BidManager bidmng = new BidManager();
		AuctionManager aucmng = new AuctionManager();
		Auction auction;
		Bid bid, previousBid;
		User user, previousBidder;
		int offer = Integer.valueOf(request.getParameter("offer"));
		int auctionId = Integer.valueOf(request.getParameter("auctionId"));
		try {
			user = ((User) session.getAttribute("user"));
			if (user.getBalance() < offer) {
				// TODO : add error msg "insufficient funds"
				doGet(request, response);
			} else {
				previousBid = bidmng.getMaxBid(auctionId);
				if (offer < previousBid.getBidPrice()) {
					// TODO : add error msg "offer was too low"
					doGet(request, response);
				}
				bid = new Bid(user.getUserId(), auctionId, offer, LocalDate.now());
				bidmng.create(bid);
				user.setBalance(user.getBalance() - offer);
				usmng.update((User) session.getAttribute("user"));
				if (previousBid != null) {
					previousBidder = usmng.getById(previousBid.getUserId());
					previousBidder.setBalance(previousBidder.getBalance() + previousBid.getBidPrice());
					usmng.update(previousBidder);
				}
				auction = aucmng.getById(auctionId);
				auction.setSalePrice(offer);
				aucmng.update(auction);
				request.setAttribute("bid", bid);
			}
		} catch (BLLException | BOException e) {
			// TODO : send to 500 page
			e.printStackTrace();
		}
		/// TODO Decide what page to forward to and how
		// -> decided to let bidder choose himself by sending him on an "outcome page"?
//		RequestDispatcher rd = request.getRequestDispatcher(request.getHeader("referer"));
//		rd.forward(request, response);
		doGet(request, response);
	}
}
