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

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/bid")
public class BidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Get user from session
		// TODO Get auctionID !
		int auctionId = 5;
		// ?? Put managers inside the try block ??
		UserManager usmng = new UserManager();
		User user = new User("Uncle Scrooge", "Mc Duck", "Scrooge", "rich@mcduck.com", "09876544567", "Top of the hill",
				"$$$$$$", "Duckburg", "password", 1000, false);
		BidManager bidmng = new BidManager();
		AuctionManager aucmng = new AuctionManager();
		int offer = Integer.valueOf(request.getParameter("offer"));
		try {
			user = usmng.getByUsername("Uncle Scrooge");
			if (user.getCredit() >= offer) {

				user.setCredit(user.getCredit() - offer);
				usmng.update(user);
				bidmng.create(new Bid(user.getUserId(), auctionId, offer, LocalDate.now()));
				Bid previousBid = bidmng.getMaxBid(auctionId);
				if (previousBid != null) {
					System.out.println("PREVIOUS BID NOT NULL");
					User previousBidder = usmng.getById(previousBid.getUserId());
					previousBidder.setCredit(previousBidder.getCredit() + previousBid.getBidPrice());
				} else {
					System.out.println("PREVIOUS BID NOT FOUND");
				}
				Auction auction = aucmng.getById(auctionId);
				auction.setSalePrice(offer);
				aucmng.update(auction);
			}
		} catch (BLLException | BOException e) {
			e.printStackTrace();
		}
		/// TODO Decide what page to forward to and how
//		RequestDispatcher rd = request.getRequestDispatcher(request.getHeader("referer"));
//		rd.forward(request, response);
		response.sendRedirect(request.getServletContext().getContextPath());
	}

}
